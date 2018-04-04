package com.yh.sales.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.User;
import com.yh.sales.user.service.UserService;

@Controller
@Configuration
public class UserController {
	@Autowired
	private UserService userService;

	/*
	 * @RequestMapping(value = "/addUser", method = RequestMethod.GET) public
	 * boolean addUser(@RequestParam("loginname") String loginname,
	 * 
	 * @RequestParam("password") String password) { return
	 * userService.addUser(loginname, password); }
	 */

	/*
	 * @RequestMapping(value = "/addUserWithBackId", method = RequestMethod.GET)
	 * public User addUserWithBackId(@RequestParam("loginname") String loginname,
	 * 
	 * @RequestParam("password") String password) { return
	 * userService.addUserWithBackId(loginname, password); }
	 */

	@ResponseBody
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public User findByUserName() {
		return userService.findByUserName("zhangsan");
	}

	@RequestMapping(value = "active", method = RequestMethod.GET)
	public String active(@RequestParam("token")String token,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		if(userService.isActive(token)) {
			model.addAttribute("code", "1");
			model.addAttribute("msg", "激活链接成功，该账号可以登录了~");
		}else {
			model.addAttribute("code", "0");
			model.addAttribute("msg", "激活链接失败，该链接已过期，请重新注册！");
		}
		return "common/activate";
	}
	

}
