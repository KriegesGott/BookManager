package edu.xtu.library.controller;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.result.ResultData;
import edu.xtu.library.controller.req.AddUserReq;
import edu.xtu.library.controller.req.LoginReq;
import edu.xtu.library.controller.req.RegisterReq;
import edu.xtu.library.controller.req.UpdatePasswordReq;
import edu.xtu.library.controller.req.UpdateUserReq;
import edu.xtu.library.controller.vo.LoginVO;
import edu.xtu.library.controller.vo.UserInfoVO;
import edu.xtu.library.controller.vo.UserListVO;
import edu.xtu.library.entity.User;
import edu.xtu.library.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@PostMapping("/login")
	public ResultData<LoginVO> login(@RequestBody LoginReq loginReq) throws ProjectException {
		return new ResultData().success(userService.login(loginReq));
	}

	@PostMapping("/register")
	public ResultData<String> register(@RequestBody RegisterReq registerReq) throws ProjectException {
		String token = userService.register(registerReq);
		return new ResultData().success(token);
	}

	@GetMapping("/userList")
	public ResultData<List<User>> userList(){
		return new ResultData().success(userService.list());
	}

	@GetMapping("/search")
	public ResultData<List<User>> search(@RequestParam String text){
		return new ResultData().success(userService.search(text));
	}

	@GetMapping("/getName")
	public ResultData<String> getName() throws ProjectException {
		return new ResultData().success(userService.getName());
	}

	@PostMapping("/addUser")
	public ResultData<String> addUser(@RequestBody AddUserReq req) throws ProjectException {
		userService.addUser(req);
		return ResultData.success();
	}

	@PostMapping("/updateUser")
	public ResultData<String> updateUser(@RequestBody UpdateUserReq req) throws ProjectException {
		userService.updateUser(req);
		return ResultData.success();
	}

	@PostMapping("/updatePassword")
	public ResultData<String> updatePassword(@RequestBody UpdatePasswordReq req) throws ProjectException {
		userService.updatePassword(req);
		return ResultData.success();
	}

	@GetMapping("/delete")
	public ResultData<String> delete(@RequestParam Long id) throws ProjectException {
		userService.delete(id);
		return ResultData.success();
	}

	@GetMapping("/userInfo")
	public ResultData<UserInfoVO> getUserInfo(){
		return new ResultData().success(userService.getUserInfo());
	}

}
