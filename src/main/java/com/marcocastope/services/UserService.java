package com.marcocastope.services;

import com.marcocastope.model.User;

public interface UserService extends Crud<User> {

	public User saveUser(User user);

	public User findByUsername(String username);

	public User findByLoginAndPassword(String username, String password);
}
