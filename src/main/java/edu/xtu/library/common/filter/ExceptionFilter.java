package edu.xtu.library.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import edu.xtu.library.common.exception.ProjectException;
import edu.xtu.library.common.result.ResultData;

import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			filterChain.doFilter(request, response);
		} catch (ProjectException e) {
			ResultData<String> result = ResultData.fail(e);
			response.getWriter().write(JSON.toJSONString(result));
		}
	}
}
