package com.prac.accountservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("account-service")
@RefreshScope
public class HelloController {

	@Value("${hello.message}")
	private String helloMessage;

	@Value("${server.port}")
	private int serverPort;

	@GetMapping("/hello/message")
	public String hello(HttpServletRequest request) {
		return this.helloMessage + " from Account service with port " + request.getLocalPort();
	}
}
