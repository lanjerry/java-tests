package com.lanjerry.mybatis.dao;

import com.lanjerry.mybatis.po.User;

public interface UserDao {
	
	//根据id查询用户信息
	public User findUserByid(int id) throws Exception;
	
	//添加用户信息
	public void insertUser(User user) throws Exception;
	
	//删除用户信息
	public void deleteUser(int id) throws Exception;
}
