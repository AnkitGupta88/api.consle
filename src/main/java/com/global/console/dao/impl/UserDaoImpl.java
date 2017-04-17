package com.global.console.dao.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.console.dao.UserDao;
import com.global.console.dto.UserDetail;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	
	public UserDaoImpl() {
	}

	@Override
	public void addUser(UserDetail userDetail, String id, String key){
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDetail, User.class);
		user.setId(id);
		user.setKey(key);
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void updateUserKey(String userId, String key) {
		User user = userRepository.findById(userId);
		user.setKey(key);
		userRepository.save(user);
	}

}
