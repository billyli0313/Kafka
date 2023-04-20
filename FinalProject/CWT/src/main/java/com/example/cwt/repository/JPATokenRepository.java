package com.example.cwt.repository;

import com.example.cwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPATokenRepository extends JpaRepository<Token,String> {
    Token findByEmail(String email);
}
