package com.yh.commodity.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yh.commodity.service.CommodityService;
import com.yh.entity.Commodity;

@Controller
@Configuration
public class CommodityController {
	/*@Autowired
	private CommodityService commodityService;
	
	@PostMapping("saveCommodity")
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
