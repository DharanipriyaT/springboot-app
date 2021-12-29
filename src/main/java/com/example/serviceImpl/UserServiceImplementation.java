package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service(value = "userService")
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void createUser(User user) {
		Optional<User> userOpt = userRepository.findById(user.getId());
		if (userOpt.isPresent()) {
			ResponseEntity.unprocessableEntity().body("Id already exist");
		} else {
			User userCreate = new User();
			userCreate.setName(user.getName());
			userCreate.setEmail(user.getEmail());
			userCreate.setPhoneNumber(user.getPhoneNumber());
			userRepository.save(userCreate);
		}
	}

	@Override
	public User getUserById(Long id) {

		User user = null;
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers(String name) {
		List<User> users = null;
		if (name != null) {
			users = userRepository.findByName(name);

		} else {
			users = userRepository.findAll();
		}
		return users;
	}

	@Override
	public void deleteUserById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			userRepository.delete(user);
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public User updateAll(User userReq) {
		User user = null;
		Optional<User> userOpt = userRepository.findById(userReq.getId());
		if (userOpt.isPresent()) {
			user = userOpt.get();
			if (!user.getName().isEmpty()) {
				user.setName(user.getName());
			}
			if (!user.getEmail().isEmpty()) {
				user.setEmail(user.getEmail());
			}
			if (!user.getPhoneNumber().isEmpty()) {
				user.setPhoneNumber(user.getPhoneNumber());
			}
			userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		return user;

	}

}
