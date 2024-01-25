package com.prac.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prac.userservice.model.User;
import com.prac.userservice.repo.UserStreamsRepo;

@Service
public class UserService {

	@Autowired
	UserStreamsRepo userStreamsRepo;

	public List<User> findAll() {
		return this.userStreamsRepo.findAll();
	}

	public User findById(int userId) {
		return this.userStreamsRepo.findById(userId).orElse(null);
	}

	public void deleteById(int userId) {
		this.userStreamsRepo.deleteById(userId);
	}

	public void save(User user) {
		this.userStreamsRepo.save(user);
	}

	public void save(List<User> users) {
		this.userStreamsRepo.save(users);
	}

}
