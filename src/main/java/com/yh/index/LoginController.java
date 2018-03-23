package com.yh.index;

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

import com.yh.entity.User;
import com.yh.sales.user.service.UserService;

@Controller
@Configuration
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	private String goLogin(Model map) {
		return "login";
	}
	
	@RequestMapping(value="register.html",method=RequestMethod.GET)
	private String goRegister(Model map) {
		return "register";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model,HttpServletRequest request, HttpSession session) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		//String remember=request.getParameter("remember-me");   
		//model.addAttribute("message","This is your message");

		Map<String,Object> map=new HashMap<>();
		if(account.isEmpty() || account==null) {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "用户名为空");
			return "login";
		}
		if(password.isEmpty() || password==null) {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "密码为空");
			return "login";
		}
		User user=userService.login(account, password);
		
		if(user==null || user.equals("") || user.getUserName()==null) {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "用户名或密码错误");
			return "login";
		}
		//session.setMaxInactiveInterval(interval);//秒
		session.setAttribute("user", user);
		model.addAttribute("code", "1");
		model.addAttribute("msg", "登录成功");
		/*if(remember.equals("true")) {
			//cookie  存入cookie
		}*/
		return "home";
	}
		
		

}
