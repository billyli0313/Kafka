package com.example.concur.repository;

import com.example.concur.entity.Geography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAGeographyRepository extends JpaRepository<Geography,String> {
}
