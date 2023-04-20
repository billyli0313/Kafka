package com.example.customersystem.controller;

import com.example.customersystem.entity.Login;
import com.example.customersystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestControllerAdvice
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String start(){
        return "Welcome to TRAVEL–CWT System!";
    }

    @PostMapping(value = "/login")
    public String sendMessage(@Valid @RequestBody Login login) throws Exception {
//Check login content is empty or not
        if(login.getUsername().isEmpty() || login.getPassword().isEmpty()){
            throw new BadCredentialsException( "Username or Password can not be empty!");
        }
//Check username registered or not
        try{
            loginService.loadUserByUsername(login.getUsername());

        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
//Check password
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getUsername(),
                            login.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage() + " : Login password error!");
        }
        loginService.sendLoginInfo(login);

       return "Send message successfully!";

    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleBadCredentialsException(BadCredentialsException e){
        return e.getMessage();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleUsernameNotFoundException(UsernameNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(Exception e){
        return "Sorry, there is an issue: " + e.getMessage();
    }
}
