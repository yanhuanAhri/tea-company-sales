package com.yh.core.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yh.entity.User;

//@Configuration // extends HandlerInterceptorAdapter
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final List<String> FILE_TYPE = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".ico", ".html",
			".css", ".map", ".js", ".woff", ".woff2", ".ttf", ".xls", ".xlsx", ".doc", ".docx", ".txt", ".ppt",
			".pptx");
	
	private static final List<String> PASS_PATH=Arrays.asList("/introduction","/home","/login.html","/login");
	 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		 String url = request.getRequestURI(); 
		 if(isPass(url)){  
	         //如果是公开地址则放行  
	         return true;  
	      }  
		 HttpSession session = request.getSession();  
		 User user=(User) session.getAttribute("user");
		 if(user!= null && user.getUserName()!=null && !user.getUserName().isEmpty()) {
			 return true;
		 }
		 //request.getRequestDispatcher("login.html").forward(request, response);
		   /*if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有x-requested-with  
	            PrintWriter out = ((ServletResponse) request).getWriter();  
	            out.print("login");//session失效
	            out.flush();
	            return false;
	        }else{*/
	            //非ajax请求时，session失效的处理
        	response.sendRedirect(request.getContextPath()+"/login.html");
	     //  }
		 

		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//String url = request.getRequestURI(); 
		//response.sendRedirect("http://localhost:5050"+"/login");
		//System.out.println();
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		/*String url = request.getRequestURI(); 
		System.out.println();*/
	}
	
	private boolean isPass(String path) {
		if(PASS_PATH.contains(path)) {
			return true;
		}
		return false;
	}
}
