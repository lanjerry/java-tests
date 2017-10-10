package com.lanjerry.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.service.ItemsService;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	// 商品查询列表
	// @RequestMapping实现映射器的映射url地址，一般建议讲url和方法名写成一样
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();

		// 相当于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}
}
