package com.kaisn.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaisn.dao.UserMapper;
import com.kaisn.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	public User getUserInfo(String userName, String password) {
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("userName", userName);
		userMap.put("password", password);
		return userMapper.getUserInfo(userMap);
	}

}
