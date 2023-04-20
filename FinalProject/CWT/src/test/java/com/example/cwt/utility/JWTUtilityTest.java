package com.example.cwt.utility;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class JWTUtilityTest {
    private static final long serialVersionUID = 234234523523L;
    public static final long JWT_TOKEN_VALIDITY = 3*60;
    private String secret = "secret";
    @InjectMocks
    private JWTUtility jwtUtility;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void generateTokenTest(){
        String email = "Shanna@melissa.tv";
        String token = jwtUtility.generateToken(email);
        assertThat(token).contains("eyJhbGciOiJIUzUxMiJ9");

    }
}
