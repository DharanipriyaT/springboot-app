package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {

	void createUser(User user);

	User getUserById(Long id);

	List<User> getAllUsers(String name);

	void deleteUserById(Long id);

	User updateAll(User user);

}
