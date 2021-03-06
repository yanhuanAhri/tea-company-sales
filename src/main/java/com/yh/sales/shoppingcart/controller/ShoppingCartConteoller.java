package com.yh.sales.shoppingcart.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.entity.User;
import com.yh.sales.shoppingcart.service.ShoppingCartService;

@Controller
@Configuration
public class ShoppingCartConteoller {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("shoppingCart.html")
	public String GoShopcart(Model model) {
		return "sales/shopcart";
	}
	
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
				shoppingCartService.saveShoppingCart(commodityNum, Integer.valueOf(buyNum), user);
				map.put("code", "1");
				map.put("msg", "加入购物车成功");
				session.setAttribute("shopCartCount", shoppingCartService.getCount(user, null).get("count"));
				return map;
	}
	
	@GetMapping("shoppingCart")
	@ResponseBody
	public Map<String,Object> GoShoppingCart(HttpServletResponse response,HttpSession session){
		Map<String,Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user");
			map=shoppingCartService.getShoppingCart(user);
			map.put("code", "1");
		return map;
	}
	
	@DeleteMapping("delShoppingCart")
	@ResponseBody
	public Map<String,Object> delShopping(@RequestParam("commodityNums")List<String> commodityNums,
	Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		User user=(User) session.getAttribute("user");
		Map<String,Object> map=new HashMap<>();
		shoppingCartService.delShopping(commodityNums, user);
		session.setAttribute("shopCartCount", shoppingCartService.getCount(user, null).get("count"));
		map.put("code", "1");
		return map;
	}
	
	@GetMapping("shoppingCartCount")
	@ResponseBody
	public Map<String,Object> getCount(Model model,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User user=(User) session.getAttribute("user");
		Map<String,Object> map=new HashMap<>();
		if(user==null ) {
			return map;
		}
		map=shoppingCartService.getCount(user, null);
		map.put("code", "1");
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
