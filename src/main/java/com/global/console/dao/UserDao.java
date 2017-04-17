/**
 * 
 */
package com.global.console.dao;

import java.util.List;

import com.global.console.dto.UserDetail;
import com.global.console.model.User;

/**
 * @author ankit.gupta4
 *
 */
public interface UserDao {
	
	public void addUser(UserDetail userDetail, String id, String key);
	
	public void updateUserKey(String userId, String key);

	public List<User> findAll();
}
