package com.yh.sales.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.ShoppingCartVo;
import com.yh.entity.User;
import com.yh.sales.order.service.OrderService;

@Configuration
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//跳转结算界面静态路由，需删除
	/*@GetMapping("pay.html")
	public String GoPay(Model model) {
		return "sales/pay";
	}*/
	
	@PostMapping("buyCommodity")
	public String toPay(@RequestBody String msg,Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		User user=(User) session.getAttribute("user");
		List<ShoppingCartVo> list=orderService.toBepaidMsg(msg, user);
		model.addAttribute("buyCommodityList", list);
		return "sales/pay";
	}
	
	@PostMapping("createOrder")
	@ResponseBody
	public Map<String,Object> createOrder(@RequestBody String msg,Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Map<String,Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user");
		orderService.createOrder(msg, user);
		return map;
	}
}
