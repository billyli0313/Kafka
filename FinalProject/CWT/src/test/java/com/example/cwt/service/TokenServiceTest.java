package com.example.cwt.service;

import com.example.cwt.entity.Token;
import com.example.cwt.repository.JPATokenRepository;
import com.example.cwt.utility.JWTUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @Mock
    private JPATokenRepository jpaTokenRepository;
    @Mock
    private JWTUtility jwtUtility;
    @Mock
    private KafkaTemplate<String,String> kafkaTemplate;
    @InjectMocks
    private TokenService tokenService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findByEmailNullTest(){
        Token tokenTable = null;
        Mockito.when(jpaTokenRepository.findByEmail(anyString()))
                .thenReturn(tokenTable);
        tokenService.getTokenByEmail("Rey.Padberg@karina.biz");
    }
    @Test
    public void findByEmailTest(){
        Token tokenTable = new Token("Rey.Padberg@karina.biz","EQeO3PaFXlweeCnjbNUqJXg",new Date(System.currentTimeMillis()+1000*60*10));
        Mockito.when(jpaTokenRepository.findByEmail(anyString()))
                .thenReturn(tokenTable);
        Token token = tokenService.getTokenByEmail("Rey.Padberg@karina.biz");
        assertThat(token.getToken()).isEqualTo("EQeO3PaFXlweeCnjbNUqJXg");
    }
    @Test
    public void findByEmailExpireTest(){
        Token tokenTable = new Token("Rey.Padberg@karina.biz","EQeO3PaFXlweeCnjbNUqJXg",new Date(System.currentTimeMillis()));
        Mockito.when(jpaTokenRepository.findByEmail(anyString()))
                .thenReturn(tokenTable);
        tokenService.getTokenByEmail("Rey.Padberg@karina.biz");
    }
}
