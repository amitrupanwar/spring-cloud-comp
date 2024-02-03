package com.prac.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.userservice.client.AccountServiceClient;
import com.prac.userservice.client.AddressServiceClient;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user-service")
@RefreshScope
@Slf4j
public class HelloController {

	@Value("${hello.message}")
	private String helloMessage;

	@Autowired
	private AddressServiceClient addressServiceClient;

	@Autowired
	private AccountServiceClient accountServiceClient;

	@GetMapping("/hello/message")
	@Bulkhead(name = "apiBH", fallbackMethod = "apiBHFallback")
	// @TimeLimiter(name = "apiTL", fallbackMethod = "apiTLFallback")
	@RateLimiter(name = "apiRL", fallbackMethod = "apiRLFallBack")
	@CircuitBreaker(name = "apiCB", fallbackMethod = "apiCBFallback")
	@Retry(name = "apiRetry", fallbackMethod = "apiRetryFallback")
	public List<String> hello(HttpServletRequest request) {
		List<String> response = new ArrayList<String>();
		response.add(this.helloMessage + " from User service with port " + request.getLocalPort());
		response.add(this.addressServiceClient.hello());
		response.add(this.accountServiceClient.hello());
		return response;
	}

	public ResponseEntity<String> apiBHFallback(RequestNotPermitted exception) {
		log.info("Too Many requests, Bulkhead has applied. Try after sometime.");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.body("Too Many requests, Bulkhead has applied. Try after sometime.");
	}

	public ResponseEntity<String> apiRLFallBack(RequestNotPermitted exception) {
		log.info("Too Many requests, Rate limit has applied, Try after sometime.", exception);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Too Many requests, Rate limit has applied. Try after sometime.");
	}

	public ResponseEntity<String> apiCBFallBack(Exception exception) {
		log.info("Circuit break has applied, Response from fallback", exception);
		return ResponseEntity.status(HttpStatus.OK).body("Circuit break has applied, Response from fallback");
	}

	public ResponseEntity<String> apiRetryFallback(Exception exception) {
		log.info("ApiRetryFallback has applied", exception);
		return ResponseEntity.status(HttpStatus.OK).body("ApiRetryFallback has applied");
	}
}
