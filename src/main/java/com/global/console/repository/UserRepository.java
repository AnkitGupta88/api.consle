package com.global.console.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.global.console.model.User;

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

	@Override
	public List<User> findAll();

}
