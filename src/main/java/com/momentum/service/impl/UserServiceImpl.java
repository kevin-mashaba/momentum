package com.momentum.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.momentum.entity.AuditTable;
import com.momentum.entity.User;
import com.momentum.repository.UserRepository;
import com.momentum.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User user(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.validateLogin(username, password);
		return user;
	}

	@Override
	public User findUserByUserName(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.validateLogin(username, password);
		return user;
	}

	@Override
	public List<User> findByUserName(String username) {
		// TODO Auto-generated method stub

		return userRepository.findByUsername(username);
	}

	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

}
