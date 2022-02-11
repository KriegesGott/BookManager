package edu.xtu.library.common.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.xtu.library.entity.User;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

	/**
	 * 用户登录成功后生成Jwt
	 * 使用Hs256算法  私匙使用用户密码
	 * @param user      登录成功的user对象
	 * @return
	 */
	public static String createJWT(User user) {
		//指定签名的时候使用的签名算法
		Algorithm algorithmHS = Algorithm.HMAC256("qixiyuan");

		//创建payload的私有声明
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId().toString());
		claims.put("name", user.getName());
		claims.put("studentCode", user.getStudentCode());
		claims.put("department", user.getDepartment());
		claims.put("role", user.getRole());
		String token = JWT.create()
				.withPayload(claims)
				.withIssuer("qixiyuan")
				.sign(algorithmHS);

		return token;
	}


	/**
	 * Token的解密
	 * @param token 加密后的token
	 * @return
	 */
	public static User parseJWT(String token) throws JWTVerificationException {
		Algorithm algorithm = Algorithm.HMAC256("qixiyuan");
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer("qixiyuan")
				.build();
		DecodedJWT jwt = verifier.verify(token);
		User user = new User();
		user.setId(Long.parseLong(jwt.getClaim("id").asString()));
		user.setName(jwt.getClaim("name").asString());
		user.setStudentCode(jwt.getClaim("studentCode").asString());
		user.setDepartment(jwt.getClaim("department").asString());
		user.setRole(jwt.getClaim("role").asString());
		return user;

	}
}
