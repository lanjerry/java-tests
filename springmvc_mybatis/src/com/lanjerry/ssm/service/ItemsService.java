package com.lanjerry.ssm.service;

import java.util.List;

import com.lanjerry.ssm.po.ItemsCustom;
import com.lanjerry.ssm.po.ItemsQueryVo;

public interface ItemsService {
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}
