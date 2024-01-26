package com.prac.addressservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prac.addressservice.address.Address;
import com.prac.addressservice.repo.AddressStreamsRepo;

@Service
public class AddressService {

	@Autowired
	private AddressStreamsRepo addressStreamsRepo;

	public List<Address> findAll() {
		return this.addressStreamsRepo.findAll();
	}

	public Address findById(int addressId) {
		return this.addressStreamsRepo.findById(addressId).orElse(null);
	}

	public void deleteById(int addressId) {
		this.addressStreamsRepo.deleteById(addressId);
	}

	public void save(Address address) {
		this.addressStreamsRepo.save(address);
	}

	public void save(List<Address> addresses) {
		this.addressStreamsRepo.save(addresses);
	}

	public List<Address> findByUserId(int userId) {
		return this.addressStreamsRepo.findByUserId(userId);
	}

	public void deleteByUserId(int userId) {
		this.addressStreamsRepo.deleteByUserId(userId);
	}
}
