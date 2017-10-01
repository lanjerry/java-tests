package com.lanjerry.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lanjerry.mybatis.po.User;

public class MybatisFirst {

	// 根据id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() throws IOException {

		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 通过SqlSession操作数据库
		User user = sqlSession.selectOne("test.findUserById", 1);

		System.out.println(user);
		sqlSession.close();
	}

	// 根据用户名称模糊查询用户列表
	@Test
	public void findUserByNameTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> list = sqlSession.selectList("test.findUserByName", "小明");

		System.out.println(list);
		sqlSession.close();
	}

	// 添加用户
	@Test
	public void insertUserTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 插入用户对象
		User user = new User();
		user.setUsername("王大军");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("广东深圳");
		sqlSession.insert("insertUser", user);

		// 提交事务
		sqlSession.commit();

		// 获取用户自增主键
		System.out.println(user.getId());

		sqlSession.close();
	}

	// 删除用户
	@Test
	public void deleteUserTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.delete("test.deleteUser", 29);

		// 提交事务
		sqlSession.commit();

		sqlSession.close();
	}

	// 更新用户
	@Test
	public void updateUserTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";

		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 插入用户对象
		User user = new User();
		user.setId(34);
		user.setUsername("王中军");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("广东111");
		sqlSession.update("test.updateUser", user);

		// 提交事务
		sqlSession.commit();

		sqlSession.close();
	}
}
