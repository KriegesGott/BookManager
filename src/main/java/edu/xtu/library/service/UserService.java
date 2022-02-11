package edu.xtu.library.service;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.google.common.primitives.Longs;
import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
import edu.xtu.library.common.utils.JwtUtils;
import edu.xtu.library.controller.req.AddUserReq;
import edu.xtu.library.controller.req.LoginReq;
import edu.xtu.library.controller.req.RegisterReq;
import edu.xtu.library.controller.req.UpdatePasswordReq;
import edu.xtu.library.controller.req.UpdateUserReq;
import edu.xtu.library.controller.vo.LoginVO;
import edu.xtu.library.controller.vo.UserInfoVO;
import edu.xtu.library.dao.BorrowDao;
import edu.xtu.library.dao.UserDao;
import edu.xtu.library.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	@Resource
	private BorrowDao borrowDao;

	public LoginVO login(LoginReq req) throws ProjectException {
		String name = req.getName();
		String password = req.getPassword();
		if (Strings.isBlank(name) || Strings.isBlank(password)){
			throw new ProjectException("输入不能为空");
		}

		User user = userDao.selectUserByName(name);
		if (Objects.isNull(user) || !password.equals(user.getPassword())){
			throw new ProjectException("用户名或密码错误");
		}
		String token = JwtUtils.createJWT(user);
		return LoginVO.builder()
				.token(token)
				.isAdmin("管理员".equals(user.getRole()))
				.build();
	}

	public String register(RegisterReq req) throws ProjectException {
		String name = req.getName();
		String password = req.getPassword();
		String studentCode = req.getStudentCode();
		String department = req.getDepartment();
		if (Strings.isBlank(name) || Strings.isBlank(password) || Strings.isBlank(studentCode) || Strings.isBlank(department)){
			throw new ProjectException("输入不能为空");
		}
		if (!studentCode.matches("^[0-9]{12}$")){
			throw new ProjectException("学号格式错误");
		}
		User oldUser = userDao.selectUserByName(name);
		if (Objects.nonNull(oldUser)){
			throw new ProjectException("用户名已被注册");
		}

		User user = User.builder()
				.name(req.getName())
				.password(req.getPassword())
				.department(req.getDepartment())
				.studentCode(req.getStudentCode())
				.build();
		int result = userDao.insertOne(user);
		if (result != 1){
			throw new ProjectException("数据库错误");
		}
		user = userDao.selectUserByName(req.getName());
		return JwtUtils.createJWT(user);
	}

	public String getName() throws ProjectException {
		User user = UserPool.get();
		if (Objects.isNull(user)){
			throw new ProjectException(405, "请先登录");
		}
		return user.getName();
	}

	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(UpdatePasswordReq req) throws ProjectException {
		String oldPassword = req.getOldPassword();
		String newPassword = req.getNewPassword();
		String repeat = req.getRepeat();
		if (Strings.isBlank(oldPassword) || Strings.isBlank(newPassword) || Strings.isBlank(repeat)){
			throw new ProjectException("输入不能为空");
		}
		if (!repeat.equals(newPassword)){
			throw new ProjectException("两次新密码不一致");
		}

		User user = UserPool.get();
		user = userDao.selectUserByName(user.getName());
		if (!oldPassword.equals(user.getPassword())){
			throw new ProjectException("旧密码输入错误");
		}
		user.setPassword(newPassword);
		int res = userDao.updateById(user);
		if (res != 1){
			throw new ProjectException("数据库发生错误");
		}
	}

	public List<User> search(String text){
		if (Strings.isBlank(text)){
			return list();
		}
		return userDao.selectUserByText(text);
	}

	public List<User> list(){
		return userDao.selectAllUser();
	}

	public void addUser(AddUserReq req) throws ProjectException {
		String name = req.getName();
		String studentCode = req.getStudentCode();
		String department = req.getDepartment();
		if (Strings.isBlank(name) || Strings.isBlank(studentCode) || Strings.isBlank(department)){
			throw new ProjectException("输入不能为空");
		}
		User user = userDao.selectUserByName(name);
		if (Objects.nonNull(user)){
			throw new ProjectException("用户名已存在");
		}
		if (!studentCode.matches("^[0-9]{12}$")){
			throw new ProjectException("学号格式错误");
		}
		user = User.builder()
				.name(name)
				.studentCode(studentCode)
				.department(department)
				.password(studentCode)
				.build();
		int res = userDao.insertOne(user);
		if (res != 1){
			throw new ProjectException("数据库错误");
		}
	}

	public void updateUser(UpdateUserReq req) throws ProjectException {
		Long id = req.getId();
		String name = req.getName();
		String studentCode = req.getStudentCode();
		String department = req.getDepartment();
		if (Objects.isNull(id) || Strings.isBlank(name) || Strings.isBlank(studentCode) || Strings.isBlank(department)){
			throw new ProjectException("输入不能为空");
		}
		User user = userDao.selectUserById(id);
		if (!name.equals(user.getName())){
			User otherUser = userDao.selectUserByName(name);
			if (Objects.nonNull(otherUser)){
				throw new ProjectException("用户名已存在");
			}
		}
		user.setName(name);
		user.setStudentCode(studentCode);
		user.setDepartment(department);
		int res = userDao.updateById(user);
		if (res != 1){
			throw new ProjectException("数据库错误");
		}
	}

	public void delete(Long id) throws ProjectException {
		if (Objects.isNull(id)){
			throw new ProjectException("参数为空");
		}
		userDao.deleteById(id);
	}

	public UserInfoVO getUserInfo(){
		User user = UserPool.get();
		Long id = user.getId();
		int count = borrowDao.selectCountByUserId(id);
		return UserInfoVO.builder()
				.name(user.getName())
				.school("湘潭大学")
				.department(user.getDepartment())
				.role(user.getRole())
				.borrowCount("" + count)
				.build();
	}
}
