package edu.xtu.library.controller;
import java.util.List;

import javax.annotation.Resource;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.result.ResultData;
import edu.xtu.library.controller.req.AddBookReq;
import edu.xtu.library.controller.req.AddUserReq;
import edu.xtu.library.controller.req.UpdateBookReq;
import edu.xtu.library.controller.req.UpdateUserReq;
import edu.xtu.library.entity.Book;
import edu.xtu.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;

	@GetMapping("/list")
	public ResultData<List<Book>> list(){
		return new ResultData().success(bookService.list());
	}

	@GetMapping("/recommend")
	public ResultData<List<Book>> recommend() throws ProjectException {
		return new ResultData().success(bookService.recommend());
	}

	@GetMapping("/search")
	public ResultData<List<Book>> search(@RequestParam String text){
		return new ResultData().success(bookService.search(text));
	}

	@PostMapping("/addBook")
	public ResultData<String> addBook(@RequestBody AddBookReq req) throws ProjectException {
		bookService.addBook(req);
		return ResultData.success();
	}

	@PostMapping("/updateBook")
	public ResultData<String> updateBook(@RequestBody UpdateBookReq req) throws ProjectException {
		bookService.updateBook(req);
		return ResultData.success();
	}

	@GetMapping("/delete")
	public ResultData<String> delete(@RequestParam Long id) throws ProjectException{
		bookService.delete(id);
		return ResultData.success();
	}
}
