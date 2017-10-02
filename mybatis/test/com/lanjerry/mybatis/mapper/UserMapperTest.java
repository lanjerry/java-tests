package com.lanjerry.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.lanjerry.mybatis.po.User;
import com.lanjerry.mybatis.po.UserCustom;
import com.lanjerry.mybatis.po.UserQueryVo;

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
	public void testFindUserList() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//创建userQueryVo包装对象
		UserQueryVo userQueryVo=new UserQueryVo();
		UserCustom userCustom=new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("小明");
		userQueryVo.setUserCustom(userCustom);
		
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		userQueryVo.setIds(ids);
		
		//调用userMapper的方法
		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		
		session.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindUserCount() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//创建userQueryVo包装对象
		UserQueryVo userQueryVo=new UserQueryVo();
		UserCustom userCustom=new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("张三丰");
		userQueryVo.setUserCustom(userCustom);
		
		//调用userMapper的方法
		int count = userMapper.findUserCount(userQueryVo);
		
		session.close();
		
		System.out.println(count);
	}
	
	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//调用userMapper的方法
		User user = userMapper.findUserByIdResultMap(1);
		
		session.close();
		
		System.out.println(user);
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
