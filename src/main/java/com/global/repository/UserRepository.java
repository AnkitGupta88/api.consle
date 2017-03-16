package com.global.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.global.dto.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<User, String>{

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<User> findByName(String name);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findById(String id);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<User> findAll();

}
