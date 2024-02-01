package com.prac.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("address-service")
public interface AddressServiceClient {

	@GetMapping("address-service/hello/message")
	public String hello();

}
