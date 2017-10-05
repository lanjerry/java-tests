package com.lanjerry.ssm.dao;

import com.lanjerry.ssm.po.User;

public interface UserDao {
	
	//根据id查询用户信息
	public User findUserByid(int id) throws Exception;
}
