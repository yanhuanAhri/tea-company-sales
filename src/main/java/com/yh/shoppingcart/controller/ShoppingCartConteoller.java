package com.yh.shoppingcart.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Mode;
import com.yh.entity.Commodity;
import com.yh.entity.User;
import com.yh.shoppingcart.service.ShoppingCartService;

@Controller
@Configuration
public class ShoppingCartConteoller {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@PostMapping("addToShoppingCart")
	@ResponseBody
	public String addToShoppingCart(@RequestParam(name="commodityNum",required = true)String commodityNum,
			@RequestParam(name="buyNum",required = true)Integer buyNum,
			Model model,HttpServletResponse response,HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user== null || user.getUserName()==null || user.getUserName().isEmpty()) {
			//model.addAttribute("msg", "您还未登录，请先登录再将商品加入购物车");
		//	return "sales/shopcart";
			return "error";
		}else {
			shoppingCartService.saveShoppingCart(commodityNum, buyNum, user);
			//model.addAttribute("msg", "success");
			return "success";
		}
			//	return null;
		
	}
	
	
	/*@GetMapping("introduction")
	public String toCommodityIntroduction(@RequestParam(name="commodityNum",required = true)String commodityNum,
			Model model,HttpServletResponse response) {
		Map<String,Object> map=commodityService.getCommodityMsg(commodityNum);
		model.addAllAttributes(map);
		return "sales/introduction";
		///tea-company-sales/src/main/resources/templates/sales/introduction.html
	}
*/
}
