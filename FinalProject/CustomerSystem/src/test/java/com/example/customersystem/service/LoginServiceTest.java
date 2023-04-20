package com.example.customersystem.service;

import com.example.customersystem.entity.Login;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private KafkaTemplate<String,String> kafkaTemplate;
    @InjectMocks
    private LoginService loginService;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void sendLoginInfoTest(){
        Login login = new Login("Rey.Padberg@karina.biz","123456");
        loginService.sendLoginInfo(login);
        verify(kafkaTemplate, times(1)).send("topicUsername",login.getUsername());
    }
    @Test(expected= UsernameNotFoundException.class)
    public void UsernameNotFoundExceptionTest() throws Exception{
        Login loginExp = new Login("jeffrey","123456");
        when(loginService.loadUserByUsername(loginExp.getUsername())).thenThrow(UsernameNotFoundException.class);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            loginService.loadUserByUsername(loginExp.getUsername());
        });
    }
}
