/**
 * 
 */
package com.global.console.dao;

import java.util.List;

import com.global.console.dto.UserDetail;
import com.global.console.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Adds the user.
	 *
	 * @param userDetail
	 *            the user detail
	 * @param id
	 *            the id
	 * @param key
	 *            the key
	 */
	public void addUser(UserDetail userDetail, String id, String key);

	/**
	 * Edits the user.
	 *
	 * @param userDetail
	 *            the user detail
	 * @param id
	 *            the id
	 * @return the user
	 */
	public User editUser(UserDetail userDetail, String id);

	/**
	 * Delete user.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteUser(String id);

	/**
	 * Update user key.
	 *
	 * @param userId
	 *            the user id
	 * @param key
	 *            the key
	 */
	public void updateUserKey(String userId, String key);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll();
	
}
