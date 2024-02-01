package com.prac.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.userservice.client.AccountServiceClient;
import com.prac.userservice.client.AddressServiceClient;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user-service")
@RefreshScope
public class HelloController {

	@Value("${hello.message}")
	private String helloMessage;

	@Value("${server.port}")
	private int serverPort;

	@Autowired
	private AddressServiceClient addressServiceClient;

	@Autowired
	private AccountServiceClient accountServiceClient;

	@GetMapping("/hello/message")
	public List<String> hello(HttpServletRequest request) {
		List<String> response = new ArrayList<String>();
		response.add(this.helloMessage + " from User service with port " + request.getLocalPort());
		response.add(this.addressServiceClient.hello());
		response.add(this.accountServiceClient.hello());
		return response;
	}
}
