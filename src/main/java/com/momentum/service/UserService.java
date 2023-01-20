package com.momentum.service;


import java.util.List;
import com.momentum.entity.User;


public interface UserService {

	User user(String username,String password);
	User findUserByUserName(String username,String password);
	List<User> findByUserName(String username);
	List<User> findAllUsers();
	
}
