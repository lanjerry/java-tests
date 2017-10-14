package com.lanjerry.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanjerry.ssm.exception.CustomException;
import com.lanjerry.ssm.mapper.ItemsCustomMapper;
import com.lanjerry.ssm.mapper.ItemsMapper;
import com.lanjerry.ssm.po.Items;
import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.po.ItemsQueryVo;
import com.lanjerry.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsCustomMapper itemsCustomMapper;

	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsCustomMapper.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {

		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items==null) {
			throw new CustomException("修改的商品信息不存在！");
		}
		// 中间对商品信息进行业务处理
		// ....

		// 返回ItemsCusto
		ItemsCustom itemsCustom = null;
		if (items != null) {
			itemsCustom = new ItemsCustom();
			// 将items的属性值拷贝到itemsCustom
			BeanUtils.copyProperties(items, itemsCustom);
		}

		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 添加业务校验，通常在service接口对关键参数进行校验
		// 校验id是否为空,如果为空抛出异常
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
