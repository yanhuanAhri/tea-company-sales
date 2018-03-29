package com.yh.sales.commodity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yh.sales.commodity.service.CommodityService;
import com.yh.sales.shoppingcart.service.ShoppingCartService;

@Controller
@Configuration
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	/**
	 * 查看商品详情
	 * @param commodityNum
	 * @param model
	 * @param response
	 * @return
	 */
	@GetMapping("introduction")
	public String toCommodityIntroduction(@RequestParam(name="commodityNum",required = true)String commodityNum,
			Model model,HttpServletResponse response) {
		Map<String,Object> map=commodityService.getCommodityMsg(commodityNum);
		model.addAllAttributes(map);
		return "sales/introduction";
	}
	

	/**
	 * 商品搜索页面跳转
	 * @param search
	 * @param model
	 * @param response
	 * @return
	 */
	@GetMapping("searchTea")
	public String toSearch(@RequestParam(name="search",required = true)String search,
			Model model,HttpServletResponse response) {
		/*Map<String,Object> map=commodityService.searchCommodity(search, null);
		model.addAllAttributes(map);*/
		model.addAttribute("search", search);
		return "sales/search";
	}
	
	/**
	 * @param search
	 * @param msg
	 * @param model
	 * @param response
	 * @return
	 */
	@PostMapping("searchTea")
	@ResponseBody
	public Map<String,Object> getSearch(@RequestParam(name="search",required = true)String search,
			@RequestBody String msg,Model model,HttpServletResponse response){
		Map<String,Object> map=commodityService.searchCommodity(search, msg);
		map.put("search", search);
		map.put("code", "1");
		return map;
	}
	
	/*@PostMapping("saveCommodity")
	public void save(@RequestParam(name="commodity",required=true) Commodity commodity,
			HttpServletResponse response ) {
		commodityService.saveCommodity(commodity);
	}*/
	
	/*@RequestMapping(value="order",method = RequestMethod.POST)
	@ResponseBody
	public void save(@RequestParam(name = "msg", required = true) String msg,@RequestParam(name = "gathering1", required = false) MultipartFile gathering1,
			@RequestParam(name = "gathering2", required = false) MultipartFile gathering2,@RequestParam(name = "gathering3", required = false) MultipartFile gathering3,
				@RequestParam(name = "invoice", required = false) MultipartFile invoice,HttpServletResponse response ){
		try {
			closeAccountService.closeAccount(msg,gathering1,invoice,gathering2,gathering3);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

			try {
				response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
			} catch (IOException e1) {
				LOG.error(e.getMessage(), e1);
		    }
		}	
	}*/
}
