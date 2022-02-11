package edu.xtu.library.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
import edu.xtu.library.common.utils.DepartmentToType;
import edu.xtu.library.controller.req.AddBookReq;
import edu.xtu.library.controller.req.UpdateBookReq;
import edu.xtu.library.dao.BookDao;
import edu.xtu.library.entity.Book;
import edu.xtu.library.entity.User;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

	@Resource
	private BookDao bookDao;

	public List<Book> list(){
		return bookDao.selectAll();
	}

	public List<Book> recommend() throws ProjectException {
		User user = UserPool.get();
		if (Objects.isNull(user) || Strings.isBlank(user.getDepartment())){
			throw new ProjectException("请先登录");
		}
		String type = DepartmentToType.convert(user.getDepartment());
		return bookDao.selectBookByType(type);
	}

	public List<Book> search(String text){
		List<Book> res = bookDao.selectBookByText(text);
		return res;
	}

	public void addBook(AddBookReq req) throws ProjectException {
		String name = req.getName();
		String author = req.getAuthor();
		String publisher = req.getPublisher();
		String type = req.getType();
		if (Strings.isBlank(name) || Strings.isBlank(author) || Strings.isBlank(publisher) || Strings.isBlank(type)){
			throw new ProjectException("输入不能为空");
		}
		Book book = Book.builder()
				.name(name)
				.author(author)
				.publisher(publisher)
				.type(type)
				.build();
		int res = bookDao.insertOne(book);
		if (res != 1){
			throw new ProjectException("数据库错误");
		}
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
		Book book = Book.builder()
				.id(id)
				.name(name)
				.author(author)
				.publisher(publisher)
				.type(type)
				.state(state)
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
