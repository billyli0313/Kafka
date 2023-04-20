package com.example.concur.service;


import com.example.concur.entity.Company;
import com.example.concur.repository.JPACompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private JPACompanyRepository jpaCompanyRepository;

    public Company getCompanyById(String id){
        Optional<Company> company = jpaCompanyRepository.findById(id);
        if(company.isPresent()){
            return company.get();
        }
        return null;
    }

    public void saveCompany(Company company){
        jpaCompanyRepository.save(company);
    }
}
