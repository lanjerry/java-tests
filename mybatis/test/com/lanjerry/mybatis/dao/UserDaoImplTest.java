package com.lanjerry.mybatis.dao;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lanjerry.mybatis.po.User;

public class UserDaoImplTest {

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
	public void testFindUserByid() throws Exception {
		// 创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 调用UserDaof方法
		User user = userDao.findUserByid(1);

		System.out.println(user);
	}

	@Test
	public void testInsertUser() throws Exception {
		// 创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 调用UserDaof方法
		User user=new User();
		user.setUsername("林小敏");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("广东深圳");
		userDao.insertUser(user);

		System.out.println(user.getId());
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		// 创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 调用UserDaof方法
		userDao.deleteUser(27);
	}
}
