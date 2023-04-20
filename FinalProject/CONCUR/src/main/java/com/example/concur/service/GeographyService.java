package com.example.concur.service;

import com.example.concur.entity.Geography;
import com.example.concur.repository.JPAGeographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeographyService {
    @Autowired
    private JPAGeographyRepository jpaGeographyRepository;

    public Geography getGeographyById(String id){
        Optional<Geography> geography = jpaGeographyRepository.findById(id);
        if(geography.isPresent()){
            return geography.get();
        }
        return null;
    }

    public void saveGeography(Geography geography){
        jpaGeographyRepository.save(geography);
    }
}
