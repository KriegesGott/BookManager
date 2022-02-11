package edu.xtu.library.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
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
		Borrow borrow = Borrow.builder()
				.bookId(bookId)
				.userId(userId)
				.startTime(new Timestamp(startTime.getTime()))
				.endTime(new Timestamp(startTime.getTime() + 1000L * 60 * 60 * 24 * 30))
				.state(state)
				.build();
		int resBorrow = borrowDao.insertOne(borrow);
		int resBook = bookDao.updateById(book);
		if (resBook != 1 || resBorrow != 1){
			throw new ProjectException("数据库错误");
		}
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

	public List<UserBorrowVO> userBorrow(){
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
							.bookName(book.getName())
							.author(book.getAuthor())
							.publisher(book.getPublisher())
							.startTime(it.getStartTime())
							.endTime(it.getEndTime())
							.state(it.getState())
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




}
