package com.prac.userservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prac.userservice.model.User;

@Service
public class UserStreamsRepo {
	private List<User> userStorage = new ArrayList<>();

	public List<User> findAll() {
		return userStorage;
	}

	public Optional<User> findById(int userId) {
		return userStorage.stream().filter(u -> u.getUserId() == userId).findAny();
	}

	public void deleteById(int userId) {
		userStorage = userStorage.stream().filter(u -> u.getUserId() != userId).toList();
	}

	public void save(User user) {
		this.insertOrUpdate(user);
	}

	public void save(List<User> users) {
		users.forEach(user -> this.insertOrUpdate(user));
	}

	private void insertOrUpdate(User user) {
		if (userStorage.stream().anyMatch(x -> user.getUserId() == x.getUserId())) {
			this.deleteById(user.getUserId());
		}
		this.userStorage.add(user);
	}

}
