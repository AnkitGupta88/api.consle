package com.global.console.dao.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.console.dao.UserDao;
import com.global.console.dto.UserDetail;
import com.global.console.model.User;
import com.global.console.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl implements UserDao {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.global.console.dao.UserDao#addUser(com.global.console.dto.UserDetail,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void addUser(UserDetail userDetail, String id, String key) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDetail, User.class);
		user.setId(id);
		user.setKey(key);
		userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.UserDao#findAll()
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.UserDao#updateUserKey(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void updateUserKey(String userId, String key) {
		User user = userRepository.findById(userId);
		user.setKey(key);
		userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.UserDao#editUser(com.global.console.dto.
	 * UserDetail, java.lang.String)
	 */
	@Override
	public User editUser(UserDetail userDetail, String id) {

		User user = userRepository.findById(id);
		user.setFirstName((userDetail.getFirstName() == null ? user.getFirstName() : userDetail.getFirstName()));
		user.setLastName((userDetail.getLastName() == null ? user.getLastName() : userDetail.getLastName()));
		user.setEmailId((userDetail.getEmailId() == null ? user.getEmailId() : userDetail.getEmailId()));
		user.setPhoneNo((userDetail.getPhoneNo() == null ? user.getPhoneNo() : userDetail.getPhoneNo()));
		user.setRole((userDetail.getRole() == null ? user.getRole() : userDetail.getRole()));
		userRepository.save(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.global.console.dao.UserDao#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String id) {
		userRepository.delete(id);
	}

}
