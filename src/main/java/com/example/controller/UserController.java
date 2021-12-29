package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		userService.createUser(user);

		return ResponseEntity.ok().body("user Inserted Successfully");

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(user);

	}

	@GetMapping("/users")
	public List<User> getAllUsers(@RequestParam(required = false) String name) {
		List<User> users = userService.getAllUsers(name);

		if (users.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		return users;

	}

	@PutMapping("/user")
	public ResponseEntity<?> updateUserAllDetails(@RequestBody User user) {

		userService.updateAll(user);

		return ResponseEntity.ok().body("user Updated Successfully");

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

		userService.updateAll(user);

		return ResponseEntity.ok().body("user Updated Successfully");

	}

	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

}
