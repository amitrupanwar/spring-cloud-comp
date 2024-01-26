package com.prac.accountservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prac.accountservice.model.Account;
import com.prac.accountservice.repo.AccountStreamsRepo;

@Service
public class AccountService {

	@Autowired
	private AccountStreamsRepo accountStreamsRepo;

	public List<Account> findAll() {
		return this.accountStreamsRepo.findAll();
	}

	public Optional<Account> findById(int accountId) {
		return this.accountStreamsRepo.findById(accountId);
	}

	public void deleteById(int accountId) {
		this.accountStreamsRepo.deleteById(accountId);
	}

	public void save(Account account) {
		this.accountStreamsRepo.save(account);
	}

	public void save(List<Account> accounts) {
		this.accountStreamsRepo.save(accounts);
	}

	public List<Account> findByUserId(int userId) {
		return this.accountStreamsRepo.findByUserId(userId);
	}

	public void deleteByUserId(int userId) {
		this.accountStreamsRepo.deleteByUserId(userId);
	}
}
