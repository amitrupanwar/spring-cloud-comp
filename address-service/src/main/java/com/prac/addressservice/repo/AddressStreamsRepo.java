package com.prac.addressservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prac.addressservice.address.Address;

@Service
public class AddressStreamsRepo {

	private List<Address> addressStorage = new ArrayList<>();
	
	public List<Address> findAll(){
		return this.addressStorage;
	}
	
	public Optional<Address> findById(int addressId) {
		
		return this.addressStorage.stream().filter(x-> x.getAddressId() == addressId).findFirst();
		
	}
	
}
