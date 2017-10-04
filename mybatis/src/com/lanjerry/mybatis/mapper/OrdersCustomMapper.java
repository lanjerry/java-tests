package com.lanjerry.mybatis.mapper;

import java.util.List;

import com.lanjerry.mybatis.po.Orders;
import com.lanjerry.mybatis.po.OrdersCustom;
import com.lanjerry.mybatis.po.User;

public interface OrdersCustomMapper {
	//查询订单关联用户信息，使用resultType
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	//查询订单关联用户信息，使用resultMap
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	//查询订单关联用户信息和订单明细信息，使用resultMap
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
	
	//查询用户购买的商品信息
	public List<User> findUserAndItemsResultMap() throws Exception;
}
