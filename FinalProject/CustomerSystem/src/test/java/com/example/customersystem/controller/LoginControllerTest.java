package com.example.customersystem.controller;

import com.example.customersystem.entity.Login;
import com.example.customersystem.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private LoginController loginController;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void sendMessageTest() throws Exception {
        Login login = new Login("Rey.Padberg@karina.biz","123456");
        loginController.sendMessage(login);
        verify(loginService, times(1)).sendLoginInfo(login);
    }
    @Test(expected=BadCredentialsException.class)
    public void EmptyExceptionTest() throws Exception{
        Login loginExp = new Login("","Rey.Padberg@karina.biz1");
        when(loginController.sendMessage(loginExp)).thenThrow(BadCredentialsException.class);
        Assertions.assertThrows(BadCredentialsException.class, () -> {
            loginController.sendMessage(loginExp);
        });
    }
    @Test(expected=UsernameNotFoundException.class)
    public void UsernameExceptionTest() throws Exception{
        Login loginExp = new Login("Rey","Rey.Padberg@karina.biz1");
        when(loginService.loadUserByUsername(loginExp.getUsername())).thenThrow(UsernameNotFoundException.class);
        loginController.sendMessage(loginExp);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            loginController.sendMessage(loginExp);
        });
    }
    @Test(expected=BadCredentialsException.class)
    public void PasswordExceptionTest() throws Exception{
        Login loginExp = new Login("Rey.Padberg@karina.biz","987665");
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginExp.getUsername(),loginExp.getPassword()))).thenThrow(BadCredentialsException.class);
        loginController.sendMessage(loginExp);
        Assertions.assertThrows(BadCredentialsException.class, () -> {
            loginController.sendMessage(loginExp);
        });
    }
}
