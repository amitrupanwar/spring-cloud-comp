package com.prac.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.userservice.model.User;
import com.prac.userservice.service.UserService;

@RestController
@RequestMapping("user-service/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<User> findAll() {
		return this.userService.findAll();
	}

	@GetMapping("/{userId}")
	public User findById(@PathVariable int userId) {
		return this.userService.findById(userId);
	}

	@DeleteMapping("/{userId}")
	public String deleteById(@PathVariable int userId) {
		this.userService.deleteById(userId);
		return "SUCCESS";
	}

	@PostMapping
	public String save(@RequestBody User user) {
		this.userService.save(user);
		return "SUCCESS";
	}

	@PostMapping("/list")
	public String save(@RequestBody List<User> users) {
		this.userService.save(users);
		return "SUCCESS";
	}

}
