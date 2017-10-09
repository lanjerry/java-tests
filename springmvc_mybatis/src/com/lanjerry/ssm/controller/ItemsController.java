package com.lanjerry.ssm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.service.ItemsService;

@Controller
public class ItemsController {

	private ItemsService itemsService;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception {

		List<ItemsCustom> itemList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

}
