package com.global.console.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.global.console.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<User, String> {

	/**
	 * Find by name.
	 *
	 * @param loginId
	 *            the login id
	 * @return the list
	 */
	public List<User> findByLoginId(String loginId);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the user
	 */
	public User findById(String id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Override
	public List<User> findAll();

}
