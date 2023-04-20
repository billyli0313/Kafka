package com.example.concur.repository;

import com.example.concur.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAAddressRepository extends JpaRepository<Address,String>  {
}
