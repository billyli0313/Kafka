package com.example.concur.repository;

import com.example.concur.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPACustomerRepository extends JpaRepository<Customer,String> {
    Optional<Customer> findCustomerByEmail(String email);

}
