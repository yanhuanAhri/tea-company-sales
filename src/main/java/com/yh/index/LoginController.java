package com.yh.index;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.User;
import com.yh.user.service.UserService;

@Controller
@Configuration
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	private String goLogin(Model map) {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest request, HttpSession session) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		//String remember=request.getParameter("remember-me");
		Map<String,Object> map=new HashMap<>();
		if(account.isEmpty() || account==null) {
			map.put("code", "0");
			map.put("msg", "用户名为空");
			return map;
		}
		if(password.isEmpty() || password==null) {
			map.put("code", "0");
			map.put("msg", "密码为空");
			return map;
		}
		User user=userService.login(account, password);
		
		if(user==null&&user.equals("")) {
			map.put("code", "0");
			map.put("msg", "用户名错误");
			return map;
		}
		session.setAttribute("user", user);
		map.put("code", "1");
		map.put("msg", "登录成功");
		/*if(remember.equals("true")) {
			//cookie  存入cookie
		}*/
		return map;
	}
		
		

}
