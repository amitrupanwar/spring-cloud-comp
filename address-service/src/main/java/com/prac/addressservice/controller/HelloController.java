package com.prac.addressservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("address-service")
@RefreshScope
public class HelloController {

	@Value("${hello.message}")
	private String helloMessage;

	@GetMapping("/hello/message")
	public String hello(HttpServletRequest request) {
		return this.helloMessage + " from Address service with port " + request.getLocalPort();
	}
}
