package com.lanjerry.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lanjerry.mybatis.po.Orders;
import com.lanjerry.mybatis.po.OrdersCustom;
import com.lanjerry.mybatis.po.User;

public class OrdersCustomMapperTest {
	
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindOrdersUser() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);
		
		//调用userMapper的方法
		List<OrdersCustom> list = ordersCustomMapper.findOrdersUser();
		
		session.close();
		
		System.out.println(list);
	}

	@Test
	public void testFindOrdersUserResultMapr() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);
		
		//调用userMapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersUserResultMap();
		
		session.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);
		
		//调用userMapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersAndOrderDetailResultMap();
		
		session.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);
		
		//调用userMapper的方法
		List<User> list = ordersCustomMapper.findUserAndItemsResultMap();
		
		session.close();
		
		System.out.println(list);
	}
}
