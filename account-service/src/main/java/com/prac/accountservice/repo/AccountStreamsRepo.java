package com.prac.accountservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prac.accountservice.model.Account;

import jakarta.annotation.PostConstruct;

@Service
public class AccountStreamsRepo {

	private List<Account> accountStorage = new ArrayList<>();

	@PostConstruct
	public void initialize() {
		Account account = Account.builder().accountId(10001).userId(1001).balance(123).branch("XYZ").build();
		this.accountStorage.add(account);
		account = Account.builder().accountId(10002).userId(1002).balance(324).branch("ABC").build();
		this.accountStorage.add(account);
		account = Account.builder().accountId(10003).userId(1003).balance(1263).branch("hggh").build();
		this.accountStorage.add(account);
		account = Account.builder().accountId(10004).userId(1004).balance(16323).branch("asdfff").build();
		this.accountStorage.add(account);
	}

	public List<Account> findAll() {
		return this.accountStorage.stream().sorted((o1, o2) -> o1.getAccountId() - o2.getAccountId())
				.collect(Collectors.toList());
	}

	public Optional<Account> findById(int accountId) {
		return this.accountStorage.stream().filter(x -> x.getAccountId() == accountId).findFirst();
	}

	public void deleteById(int accountId) {
		this.accountStorage = this.accountStorage.stream().filter(x -> x.getAccountId() != accountId)
				.collect(Collectors.toList());
	}

	public void save(Account account) {
		this.insertOrUpdate(account);
	}

	public void save(List<Account> accounts) {
		accounts.forEach(x -> this.insertOrUpdate(x));
	}

	public void insertOrUpdate(Account account) {
		if (this.accountStorage.stream().anyMatch(x -> x.getAccountId() == account.getAccountId())) {
			this.deleteById(account.getAccountId());
		}
		this.accountStorage.add(account);
	}

	public List<Account> findByUserId(int userId) {
		return this.accountStorage.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
	}

	public void deleteByUserId(int userId) {
		this.accountStorage = this.accountStorage.stream().filter(x -> x.getUserId() != userId)
				.collect(Collectors.toList());
	}
}
