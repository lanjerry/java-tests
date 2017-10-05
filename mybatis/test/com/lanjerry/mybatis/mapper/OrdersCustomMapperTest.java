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

		// 创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);

		// 调用userMapper的方法
		List<OrdersCustom> list = ordersCustomMapper.findOrdersUser();

		session.close();

		System.out.println(list);
	}

	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();

		// 创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);

		// 调用userMapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersUserResultMap();

		session.close();

		System.out.println(list);
	}

	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();

		// 创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);

		// 调用userMapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersAndOrderDetailResultMap();

		session.close();

		System.out.println(list);
	}

	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();

		// 创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);

		// 调用userMapper的方法
		List<User> list = ordersCustomMapper.findUserAndItemsResultMap();

		session.close();

		System.out.println(list);
	}

	@Test
	public void testFindOrderUserLazyloading() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();

		// 创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);

		// 调用userMapper的方法
		List<Orders> list = ordersCustomMapper.findOrderUserLazyloading();

		for (Orders orders : list) {
			User user = orders.getUser();
			System.out.println(user);
		}

		session.close();

		System.out.println(list);
	}

	@Test
	public void testCache1() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();

		UserMapper userMapper = session.getMapper(UserMapper.class);

		// 下边查询使用一个SqlSession
		// 第一次发起请求,查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);

		// 更新user1对象
		userMapper.updateUser(user1);

		// 执行commit操作清空缓存
		session.commit();

		// 第二次发起请求,查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
	}

	@Test
	public void testCache2() throws Exception {
		SqlSession session1 = sqlSessionFactory.openSession();
		SqlSession session2 = sqlSessionFactory.openSession();
		SqlSession session3 = sqlSessionFactory.openSession();

		UserMapper userMapper1 = session1.getMapper(UserMapper.class);

		// 下边查询使用一个SqlSession
		// 第一次发起请求,查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);

		// 这里执行关闭操作,将SqlSession的数据写到二级缓存中去
		session1.close();

		// 使用SqlSession3执行commit()操作
		/*
		 * UserMapper userMapper3 = session3.getMapper(UserMapper.class); User
		 * user=userMapper3.findUserById(1); user.setUsername("118");
		 * userMapper3.updateUser(user); session3.commit(); session3.close();
		 */

		UserMapper userMapper2 = session2.getMapper(UserMapper.class);
		// 第二次发起请求,查询id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
	}
}
