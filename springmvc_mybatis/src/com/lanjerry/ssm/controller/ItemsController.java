package com.lanjerry.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.service.ItemsService;

@Controller
//为了对url进行分类管理,可以在这里定义根路径,最终访问url是根路径+子路径
//比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	// 商品查询列表
	// @RequestMapping实现映射器的映射url地址，一般建议讲url和方法名写成一样
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception {
		
		//测试forward后request是否可以共享
		System.out.println(request.getParameter("id"));
		
		System.out.println(request.getParameter("testname"));

		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();

		// 相当于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}

	// 商品信息修改页面显示
	/*@RequestMapping("/editItems")*/
	//限制http请求方法
	/*@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems() throws Exception {

		// 调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(1);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();

		// 将商品信息放到Model
		modelAndView.addObject("itemsCustom", itemsCustom);

		// 商品修改页面
		modelAndView.setViewName("items/editItems");

		return modelAndView;
	}*/
	
	//@RequestParam里边指定request传入参数名称和形参进行绑定
	//通过required属性指定参数是否必须传入
	//通过defaultValue属性设置默认值，如果id没有参数传入，将默认值和形参绑定
	@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
	public String editItems(Model model,@RequestParam(value="id",required=true,defaultValue="1") Integer items_id) throws Exception {

		// 调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);

		//通过形参中的model将model数据传到页面
		//相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom", itemsCustom);


		return "items/editItems";
	}

	// 商品信息修改提交
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception {

		// 调用service更新商品信息,页面需要将商品信息传到此方法
		 itemsService.updateItems(id, itemsCustom);

		//重定向 
		/*return "redirect:queryItems.action";*/
		
		//页面转发
		//return "forward:queryItems.action";
		return "success";
	}
}
