package com.prac.addressservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.addressservice.address.Address;
import com.prac.addressservice.service.AddressService;

@RestController
@RequestMapping("/address-service/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public List<Address> findAll() {
		return this.addressService.findAll();
	}

	@GetMapping("/{addressId}")
	public Address findById(@PathVariable int addressId) {
		return this.addressService.findById(addressId);
	}

	@DeleteMapping("/{addressId}")
	public String deleteById(@PathVariable int addressId) {
		this.addressService.deleteById(addressId);
		return "SUCCESS";
	}

	@PostMapping
	public String save(@RequestBody Address address) {
		this.addressService.save(address);
		return "SUCCESS";
	}

	@PostMapping("/list")
	public String save(@RequestBody List<Address> addresses) {
		this.addressService.save(addresses);
		return "SUCCESS";
	}

	@GetMapping("/by-user-id/{userId}")
	public List<Address> findByUserId(@PathVariable int userId) {
		return this.addressService.findByUserId(userId);
	}

	@DeleteMapping("/by-user-id/{userId}")
	public String deleteByUserId(@PathVariable int userId) {
		this.addressService.deleteByUserId(userId);
		return "SUCCESS";
	}

}
