package com.lanjerry.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 进入Handler方法之前执行
	// 用于身份认证、身份授权
	// 比如身份认证，如果认证通过表示当前用户没有登录，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURI();

		// 判断url是否公开地址
		if (url.indexOf("login.action") >= 0) {
			return true;
		}

		// 判断session
		HttpSession session = request.getSession();
		// 从session中取出用户身份信息
		String username = (String) session.getAttribute("username");

		if (username != null) {
			// 身份存在，放行
			return true;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

		return false;
	}

}
