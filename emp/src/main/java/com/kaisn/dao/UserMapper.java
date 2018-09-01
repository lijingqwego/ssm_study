package com.kaisn.dao;

import java.util.Map;

import com.kaisn.pojo.User;

public interface UserMapper {

	User getUserInfo(Map<String, String> userMap);
}
