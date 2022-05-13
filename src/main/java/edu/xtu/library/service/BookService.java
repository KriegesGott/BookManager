package edu.xtu.library.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.google.common.primitives.Doubles;
import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
import edu.xtu.library.common.utils.DepartmentToType;
import edu.xtu.library.controller.req.AddBookReq;
import edu.xtu.library.controller.req.UpdateBookReq;
import edu.xtu.library.controller.vo.UserListVO;
import edu.xtu.library.dao.BookDao;
import edu.xtu.library.dao.HistoryDao;
import edu.xtu.library.entity.Book;
import edu.xtu.library.entity.History;
import edu.xtu.library.entity.User;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

	@Resource
	private BookDao bookDao;

	@Resource
	private HistoryDao historyDao;

	@Resource
	private TypeService typeService;

	public List<Book> list(){
		return bookDao.selectAll();
	}

	public List<UserListVO> recommend() throws ProjectException {
		User user = UserPool.get();
		if (Objects.isNull(user) || Strings.isBlank(user.getDepartment())){
			throw new ProjectException("请先登录");
		}
		String type = historyDao.selectByUser(user.getId());
		if (Strings.isBlank(type)){
			type = DepartmentToType.convert(user.getDepartment());
		}
		return bookDao.selectUserBookByType(type);
	}

	public List<UserListVO> search(String text){
		User user = UserPool.get();
		List<UserListVO> res = bookDao.selectBookByText(text);
		String type = "";
		if (res.size() != 0){
			type = res.get(0).getType();
			Map<String, Integer> map = new HashMap<>();
			for (UserListVO book : res){
				if (map.keySet().contains(book.getType())){
					map.put(book.getType(), map.get(book.getType()) + 1);
				}else{
					map.put(book.getType(), 1);
				}
			}
			int max = 0;
			for (String typeOne : map.keySet()){
				if (map.get(typeOne) > max){
					type = typeOne;
					max = map.get(typeOne);
				}
			}
		}
		History history = History.builder()
				.userId(user.getId())
				.text(text)
				.recommend(type)
				.build();
		historyDao.insertOne(history);
		return res;
	}

	public void addBook(AddBookReq req) throws ProjectException {
		String name = req.getName();
		String author = req.getAuthor();
		String publisher = req.getPublisher();
		String code = req.getCode();
		Double price = req.getPrice();
		Integer number = req.getNumber();

		if (Strings.isBlank(name) || Strings.isBlank(author) || Strings.isBlank(publisher)
			|| Strings.isBlank(code) || Objects.isNull(price) || Objects.isNull(number)){
			throw new ProjectException("输入不能为空");
		}
		User user = UserPool.get();
		String type = typeService.getName(code);
		for (int i = 0; i < number; i ++){
			Book book = Book.builder()
					.name(name)
					.author(author)
					.publisher(publisher)
					.type(type)
					.createTime(new Timestamp(System.currentTimeMillis()))
					.updateTime(new Timestamp(System.currentTimeMillis()))
					.creator(user.getName())
					.modifier(user.getName())
					.code(code)
					.price(price)
					.build();
			int res = bookDao.insertOne(book);
			if (res != 1){
				throw new ProjectException("数据库错误");
			}
		}

	}

	public List<UserListVO> userBookList(){
		return bookDao.selectUserBookList();
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateBook(UpdateBookReq req) throws ProjectException {
		Long id = req.getId();
		String author = req.getAuthor();
		String publisher = req.getPublisher();
		String name = req.getName();
		String type = req.getType();
		String state = req.getState();
		if (Strings.isBlank(author) || Strings.isBlank(publisher) || Strings.isBlank(name) ||
			Strings.isBlank(type) || Strings.isBlank(state) || Objects.isNull(id)){
			throw new ProjectException("输入不能为空");
		}
		User user = UserPool.get();
		Book book = Book.builder()
				.id(id)
				.name(name)
				.author(author)
				.publisher(publisher)
				.type(type)
				.state(state)
				.updateTime(new Timestamp(System.currentTimeMillis()))
				.modifier(user.getName())
				.build();
		int res = bookDao.updateById(book);
		if (res != 1){
			throw new ProjectException("数据库错误");
		}
	}

	public void delete(Long id) throws ProjectException {
		if (Objects.isNull(id)){
			throw new ProjectException("输入不能为空");
		}
		bookDao.deleteById(id);
	}
}
