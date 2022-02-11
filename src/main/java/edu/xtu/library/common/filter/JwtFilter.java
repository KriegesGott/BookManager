package edu.xtu.library.common.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.global.UserPool;
import edu.xtu.library.common.utils.JwtUtils;
import edu.xtu.library.entity.User;
import org.apache.logging.log4j.util.Strings;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*")
public class JwtFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (request.getRequestURI().matches("/user/login") || request.getRequestURI().matches("/user/register")){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		String token = request.getHeader("token");
		if (Strings.isBlank(token) || token.equals("null")){
			//throw new ProjectException(405, "请先登录");
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		User user = JwtUtils.parseJWT(token);
		UserPool.put(user);
		filterChain.doFilter(servletRequest, servletResponse);
		UserPool.remove();
	}
}
