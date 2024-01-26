package com.prac.addressservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prac.addressservice.address.Address;

import jakarta.annotation.PostConstruct;

@Service
public class AddressStreamsRepo {

	private List<Address> addressStorage = new ArrayList<>();

	@PostConstruct
	public void init() {
		Address address = Address.builder().addressId(101).userId(1001).addressLine1("line 1-101")
				.addressLine2("lin2 101").build();
		this.addressStorage.add(address);
		address = Address.builder().addressId(102).userId(1002).addressLine1("line 1-102").addressLine2("lin2 101")
				.build();
		this.addressStorage.add(address);
		address = Address.builder().addressId(103).userId(1003).addressLine1("line 1-103").addressLine2("lin2 101")
				.build();
		this.addressStorage.add(address);
		address = Address.builder().addressId(104).userId(1004).addressLine1("line 1-104").addressLine2("lin2 101")
				.build();
		this.addressStorage.add(address);

	}

	public List<Address> findAll() {
		return this.addressStorage.stream().sorted((o1, o2) -> o1.getAddressId() - o2.getAddressId())
				.collect(Collectors.toList());
	}

	public Optional<Address> findById(int addressId) {
		return this.addressStorage.stream().filter(x -> x.getAddressId() == addressId).findFirst();
	}

	public void deleteById(int addressId) {
		this.addressStorage = this.addressStorage.stream().filter(x -> x.getAddressId() != addressId)
				.collect(Collectors.toList());
	}

	public void save(Address address) {
		this.insertOrUpdate(address);
	}

	public void save(List<Address> addresses) {
		addresses.forEach(x -> this.insertOrUpdate(x));
	}

	public void insertOrUpdate(Address address) {
		if (this.addressStorage.stream().anyMatch(x -> x.getAddressId() == address.getAddressId())) {
			this.deleteById(address.getAddressId());
		}
		this.addressStorage.add(address);
	}

	public List<Address> findByUserId(int userId) {
		return this.addressStorage.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
	}

	public void deleteByUserId(int userId) {
		this.addressStorage = this.addressStorage.stream().filter(x -> x.getUserId() != userId)
				.collect(Collectors.toList());

	}

}
