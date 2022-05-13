package edu.xtu.library.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
import edu.xtu.library.common.utils.MailUtil;
import edu.xtu.library.controller.req.BorrowReq;
import edu.xtu.library.controller.vo.BorrowVO;
import edu.xtu.library.controller.vo.UserBorrowVO;
import edu.xtu.library.dao.BookDao;
import edu.xtu.library.dao.BorrowDao;
import edu.xtu.library.dao.UserDao;
import edu.xtu.library.entity.Book;
import edu.xtu.library.entity.Borrow;
import edu.xtu.library.entity.User;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowService {

	@Resource
	private BorrowDao borrowDao;

	@Resource
	private UserDao userDao;

	@Resource
	private BookDao bookDao;

	@Resource
	private MailUtil mailUtil;

	@Transactional(rollbackFor = Exception.class)
	public void addBorrw(BorrowReq req) throws ProjectException {
		Long bookId = req.getBookId();
		String state = req.getState();
		Date startTime = req.getStartTime();
		Long userId = req.getUserId();
		if (Objects.isNull(bookId) || Strings.isBlank(state) || Objects.isNull(startTime) || Objects.isNull(userId)){
			throw new ProjectException("输入不能为空");
		}
		if(Objects.isNull(userDao.selectUserById(userId))){
			throw new ProjectException("读者号不存在");
		}
		Book book = bookDao.selectById(bookId);
		if (Objects.isNull(book)){
			throw new ProjectException("图书号不存在");
		}
		if (state.equals("未还")){
			if (book.getState().equals("借出")){
				throw new ProjectException("该书状态为借出，添加失败");
			}
		}
		book.setState("借出");
		book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		Borrow borrow = Borrow.builder()
				.bookId(bookId)
				.userId(userId)
				.startTime(new Timestamp(startTime.getTime()))
				.endTime(new Timestamp(startTime.getTime() + 1000L * 60 * 60 * 24 * 30))
				.state(state)
				.build();
		if (borrow.getEndTime().getTime() - System.currentTimeMillis() < 0){
			Double price = 0.01 * (int)((System.currentTimeMillis() - borrow.getEndTime().getTime()) / 1000 / 60 / 60 / 24);
			borrow.setPrice(price);
			User user = userDao.selectUserById(userId);
			Double newPrice = user.getPrice() + price;
			user.setPrice(newPrice);
			userDao.updateById(user);
		}
		int resBorrow = borrowDao.insertOne(borrow);
		int resBook = bookDao.updateById(book);
		if (resBook != 1 || resBorrow != 1){
			throw new ProjectException("数据库错误");
		}
	}

	public void userBorrow(String name){
		User user = UserPool.get();
		Book book = bookDao.selectByName(name);
		Borrow borrow = Borrow.builder()
				.bookId(book.getId())
				.userId(user.getId())
				.startTime(new Timestamp(System.currentTimeMillis()))
				.endTime(new Timestamp(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
				.state("未还")
				.build();
		book.setState("借出");
		book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		bookDao.updateById(book);
		borrowDao.insertOne(borrow);
	}

	public List<BorrowVO> list(){
		List<Borrow> list = borrowDao.selectAll();
		if (Objects.isNull(list) || list.size() == 0){
			return new ArrayList<>();
		}
		return list.stream().map(BorrowVO::new).collect(Collectors.toList());
	}

	public List<BorrowVO> search(Long bookId){
		if (bookId == -1){
			return list();
		}
		List<Borrow> list = borrowDao.selectByBookId(bookId);
		if (Objects.isNull(list) || list.size() == 0){
			return new ArrayList<>();
		}
		return list.stream().map(BorrowVO::new).collect(Collectors.toList());
	}

	public List<UserBorrowVO> userBorrowHistory(){
		Long userId = UserPool.get().getId();
		List<Borrow> list = borrowDao.selectByUserId(userId);
		if (Objects.isNull(list) || list.size() == 0){
			return new ArrayList<>();
		}
		return list.stream()
				.map(BorrowVO::new)
				.map(it -> {
					Book book = bookDao.selectById(it.getBookId());
					return UserBorrowVO.builder()
							.id(it.getId())
							.bookName(book.getName())
							.author(book.getAuthor())
							.publisher(book.getPublisher())
							.startTime(it.getStartTime())
							.endTime(it.getEndTime())
							.state(it.getState())
							.price(it.getPrice())
							.build();
				}).collect(Collectors.toList());

	}

	@Transactional(rollbackFor = Exception.class)
	public void updateState(Long id) throws ProjectException {
		if (Objects.isNull(id)){
			throw new ProjectException("参数为空");
		}
		Borrow borrow = borrowDao.selectById(id);
		if (borrow.getState().equals("已还")){
			throw new ProjectException("不能重复还书");
		}
		borrow.setState("已还");
		Book book = bookDao.selectById(borrow.getBookId());
		book.setState("在馆");
		book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		int resBorrow = borrowDao.updateStaueById(borrow);
		int resBook = bookDao.updateById(book);
		if (resBook != 1 || resBorrow != 1){
			throw new ProjectException("数据库错误");
		}
	}


	public void delete(Long id) throws ProjectException {
		if (Objects.isNull(id)){
			throw new ProjectException("参数为空");
		}
		Borrow borrow = borrowDao.selectById(id);
		if (borrow.getState().equals("未还")){
			throw new ProjectException("还未还书，不能删除");
		}
		int res = borrowDao.delete(id);
		if (res != 1){
			throw new ProjectException("数据库错误");
		}
	}

	public void revert(String borrowId) throws Exception {
		Borrow borrow = borrowDao.selectById(Long.parseLong(borrowId));
		Long bookId = borrow.getBookId();
		Long userId = borrow.getUserId();
		String bookName = bookDao.selectById(bookId).getName();
		String mail = userDao.selectUserById(userId).getMail();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String borrowTime = simpleDateFormat.format(new Date(borrow.getStartTime().getTime()));
		String emailTitle = "借书超时提醒";
		String emailContent = "您于" + borrowTime + "借阅的" + bookName + "图书已逾期，请尽快归还";
		mailUtil.sendEmail(mail, emailTitle, emailContent);
	}


}
