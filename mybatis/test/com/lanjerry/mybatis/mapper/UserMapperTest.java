package com.lanjerry.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lanjerry.mybatis.po.User;

public class UserMapperTest {

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
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//调用userMapper的方法
		User user = userMapper.findUserById(1);
		
		session.close();
		
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//调用userMapper的方法
		List<User> list = userMapper.findUserByName("张三丰");
		
		session.close();
		
		System.out.println(list);
	}
}
