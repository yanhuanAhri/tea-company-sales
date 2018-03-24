package com.yh.sales.receiving.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.ReceivingInfrom;
import com.yh.entity.User;
import com.yh.sales.receiving.service.ReceivingService;

@Controller
@Configuration
public class ReceivingController {
	
	@Autowired
	private ReceivingService receivingService;
	
	@GetMapping("address.html")
	public String GoShopcart(Model model) {
		return "person/address";
	}
	
	@PostMapping("saveReceivingInfo")
	@ResponseBody
	public Map<String,Object> saveReceivingInfo(@RequestBody ReceivingInfrom receivingInfrom,
			Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Map<String,Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user");
		receivingService.saveReceiving(user, receivingInfrom);
		map.put("code", "1");
		return map;
	}
	
	@GetMapping("findUserReceiving")
	@ResponseBody
	public Map<String,Object> findUserReceiving(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user=(User) session.getAttribute("user");
		Map<String,Object> map=receivingService.findAllReceivingByUser(user);
		map.put("code", "1");
		return map;
	}
	
	/**
	 * 设置默认地址
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@GetMapping("defaultReceiving")
	@ResponseBody
	public Map<String,Object> defaultReceiving(@RequestParam("id") Long id,
			Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Map<String,Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user");
		receivingService.setDefaultAddress(id, user.getId());;
		map.put("code", "1");
		return map;
	}
	
	@GetMapping("getReceiving")
	@ResponseBody
	public Map<String,Object> getReceiving(@RequestParam("id") Long id,
			Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=receivingService.findOneReceiving(id);
		map.put("code", "1");
		return map;
	}
}
