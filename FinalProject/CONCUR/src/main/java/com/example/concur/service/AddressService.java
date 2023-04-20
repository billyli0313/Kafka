package com.example.concur.service;

import com.example.concur.entity.Address;
import com.example.concur.repository.JPAAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private JPAAddressRepository jpaAddressRepository;

    public Address getAddressById(String id){
        Optional<Address> address = jpaAddressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        return null;
    }
    public void saveAddress(Address address){
        jpaAddressRepository.save(address);
    }
}
