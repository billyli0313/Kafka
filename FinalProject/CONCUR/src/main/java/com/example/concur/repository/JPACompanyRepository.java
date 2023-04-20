package com.example.concur.repository;

import com.example.concur.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPACompanyRepository extends JpaRepository<Company,String> {
}
