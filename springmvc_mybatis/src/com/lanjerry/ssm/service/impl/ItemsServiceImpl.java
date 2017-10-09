package com.lanjerry.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lanjerry.ssm.mapper.ItemsCustomMapper;
import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.po.ItemsQueryVo;
import com.lanjerry.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsCustomMapper itemsCustomMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsCustomMapper.findItemsList(itemsQueryVo);
	}

}
