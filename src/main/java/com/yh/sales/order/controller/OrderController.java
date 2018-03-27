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
	
	/**
	 * 将要购买的商品详情展示
	 * @param msg
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@PostMapping("buyCommodity")
	public String toPay(@RequestBody String msg,Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		User user=(User) session.getAttribute("user");
		List<ShoppingCartVo> list=orderService.toBepaidMsg(msg, user);
		model.addAttribute("buyCommodityList", list);
		return "sales/pay";
	}
	
	/**
	 * 生成订单
	 * @param msg
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@PostMapping("createOrder")
	@ResponseBody
	public Map<String,Object> createOrder(@RequestBody String msg,Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user=(User) session.getAttribute("user");
		Map<String,Object> map=orderService.createOrder(msg, user);
		map.put("code", "1");
		return map;
	}
	
	/**
	 * 订单支付
	 * @param paymentCode
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@PostMapping("paymentOrder")
	public String toPaysuccess(@RequestBody String payMsg,Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(orderService.pay(user, payMsg)) {
			return "sales/paysuccess";
		}else {
			//要修改成失败界面
			return "login";
		}
		
	}
}
