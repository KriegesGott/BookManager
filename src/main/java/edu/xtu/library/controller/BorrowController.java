package edu.xtu.library.controller;

import java.util.List;

import javax.annotation.Resource;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.result.ResultData;
import edu.xtu.library.controller.req.BorrowReq;
import edu.xtu.library.controller.vo.BorrowVO;
import edu.xtu.library.controller.vo.UserBorrowVO;
import edu.xtu.library.dao.BorrowDao;
import edu.xtu.library.entity.Borrow;
import edu.xtu.library.service.BorrowService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/borrow")
public class BorrowController {

	@Resource
	private BorrowService borrowService;

	@PostMapping("/addBorrow")
	public ResultData<String> addBorrow(@RequestBody BorrowReq req) throws ProjectException {
		borrowService.addBorrw(req);
		return ResultData.success();
	}

	@GetMapping("/revert")
	public ResultData<String> revert(@RequestParam String borrowId) throws Exception {
		borrowService.revert(borrowId);
		return ResultData.success();
	}

	@GetMapping("/userBorrow")
	public ResultData<String> userBorrow(@RequestParam String name) throws ProjectException{
		borrowService.userBorrow(name);
		return ResultData.success();
	}

	@GetMapping("/updateState")
	public ResultData<String> updateState(@RequestParam Long id) throws ProjectException {
		borrowService.updateState(id);
		return ResultData.success();
	}

//	@GetMapping("/userUpdateState")
//	public ResultData<String> userUpdateState(@RequestParam String name) throws ProjectException {
//		borrowService.userUpdateState(name);
//		return ResultData.success();
//	}

	@GetMapping("/delete")
	public ResultData<String> delete(@RequestParam Long id) throws ProjectException {
		borrowService.delete(id);
		return ResultData.success();
	}

	@GetMapping("/search")
	public ResultData<List<BorrowVO>> search(@RequestParam Long bookId){
		return new ResultData().success(borrowService.search(bookId));
	}

	@GetMapping("/list")
	public ResultData<List<BorrowVO>> list(){
		return new ResultData().success(borrowService.list());
	}

	@GetMapping("/userBorrowHistory")
	public ResultData<List<UserBorrowVO>> userBorrow(){
		return new ResultData().success(borrowService.userBorrowHistory());
	}


}
