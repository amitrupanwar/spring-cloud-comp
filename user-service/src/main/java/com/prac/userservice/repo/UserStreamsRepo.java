package com.prac.userservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prac.userservice.model.User;

import jakarta.annotation.PostConstruct;

@Service
public class UserStreamsRepo {
	private List<User> userStorage = new ArrayList<>();

	@PostConstruct
	public void init() {
		User user = User.builder().userId(1001).name("anb kjk ").dob("1-1-1990").gender("M").build();
		this.userStorage.add(user);
		user = User.builder().userId(1002).name("fff kjk ").dob("1-1-1992").gender("F").build();
		this.userStorage.add(user);
		user = User.builder().userId(1003).name("anhhhb kjk ").dob("1-1-1993").gender("M").build();
		this.userStorage.add(user);
		user = User.builder().userId(1004).name("jjj kjk ").dob("1-1-1995").gender("F").build();
		this.userStorage.add(user);
	}

	public List<User> findAll() {
		return this.userStorage.stream().sorted((o1, o2) -> o1.getUserId() - o2.getUserId())
				.collect(Collectors.toList());
	}

	public Optional<User> findById(int userId) {
		return this.userStorage.stream().filter(u -> u.getUserId() == userId).findAny();
	}

	public void deleteById(int userId) {
		this.userStorage = this.userStorage.stream().filter(u -> u.getUserId() != userId).collect(Collectors.toList());
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
