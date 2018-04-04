package com.yh.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.yh.entity.User;
import com.yh.sales.shoppingcart.service.ShoppingCartService;
import com.yh.sales.user.service.SendMail;
import com.yh.sales.user.service.UserService;

@Controller
@Configuration
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private SendMail sendMail;
	
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	private String goLogin(Model map) {
		return "login";
	}
	
	@RequestMapping(value="register.html",method=RequestMethod.GET)
	private String goRegister(Model map) {
		return "register";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model,HttpServletRequest request, HttpSession session,HttpServletResponse response) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

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
		if(user.getIsActive()!=1) {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "该账号未激活，请激活该账号后再登录！");
			return "login";
		}
		session.setAttribute("user", user);
		session.setAttribute("shopCartCount", shoppingCartService.getCount(user, null).get("count"));
		model.addAttribute("code", "1");
		model.addAttribute("msg", "登录成功");
		String url=(String) session.getAttribute("url");
		if(!StringUtils.isEmpty(url)) {
			if(url.contains("?")) {
				try {
					response.sendRedirect(request.getContextPath()+url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				return url;
			}
		}
		return "home";
	}
		
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@RequestBody String msg,Model model,
			HttpServletRequest request, HttpSession session,HttpServletResponse response) {
		if(userService.register(msg)) {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "注册成功，请去邮箱查看邮件，激活该账号！");
		}else {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "该注册账号已经存在了，请不要重复注册");
		}
		return "login";
	}
		

}
