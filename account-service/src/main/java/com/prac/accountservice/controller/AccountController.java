package com.prac.accountservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.accountservice.model.Account;
import com.prac.accountservice.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account-service/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Account> findAll() {		
		return this.accountService.findAll();
	}

	@GetMapping("/{accountId}")
	public Optional<Account> findById(@PathVariable int accountId) {
		return this.accountService.findById(accountId);
	}

	@DeleteMapping("/{accountId}")
	public String deleteById(@PathVariable int accountId) {
		this.accountService.deleteById(accountId);
		return "SUCCESS";
	}

	@PostMapping
	public String save(@RequestBody Account account) {
		this.accountService.save(account);
		return "SUCCESS";
	}

	@PostMapping("/list")
	public String save(@RequestBody List<Account> accounts) {
		this.accountService.save(accounts);
		return "SUCCESS";
	}

	@GetMapping("/by-user-id/{userId}")
	public List<Account> findByUserId(@PathVariable int userId) {
		return this.accountService.findByUserId(userId);
	}

	@DeleteMapping("/by-user-id/{userId}")
	public String deleteByUserId(@PathVariable int userId) {
		this.accountService.deleteByUserId(userId);
		return "SUCCESS";
	}
}
