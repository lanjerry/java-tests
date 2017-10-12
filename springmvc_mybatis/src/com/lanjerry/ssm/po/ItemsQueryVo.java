package com.lanjerry.ssm.po;

import java.util.List;

public class ItemsQueryVo {
	
	//商品信息
	private Items items;
	
	//为了系统可拓展性，对原始生成的po进行拓展
	private ItemsCustom itemsCustom;
	
	private List<ItemsCustom> itemsList;

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
}
