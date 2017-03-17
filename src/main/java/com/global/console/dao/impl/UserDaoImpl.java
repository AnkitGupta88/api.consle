package com.global.console.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.console.dao.UserDao;
import com.global.console.dto.UserDetail;
import com.global.console.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void addUser(UserDetail userDetail){
		
	}
	
}
