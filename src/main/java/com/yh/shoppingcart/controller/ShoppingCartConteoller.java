package com.yh.shoppingcart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
	
	
	/**
	 * 加入购物车
	 * @param commodityNum
	 * @param buyNum
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@GetMapping("addToShoppingCart")
	@ResponseBody
	public Map<String,Object> addToShoppingCart(@RequestParam("commodityNum")String commodityNum,
			@RequestParam("buyNum")String buyNum,
			Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
			Map<String,Object> map=new HashMap<>();
			User user=(User) session.getAttribute("user");
			//String msg=null;
			if(user== null || user.getUserName()==null || user.getUserName().isEmpty()) {
				map.put("code", "404");
				map.put("msg", "您还没有登录该系统，请登录之后再进行该操作！！！");
				//msg= "error";
			}else {
				shoppingCartService.saveShoppingCart(commodityNum, Integer.valueOf(buyNum), user);
				map.put("code", "1");
				map.put("msg", "加入购物车成功");
			}
				return map;
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
