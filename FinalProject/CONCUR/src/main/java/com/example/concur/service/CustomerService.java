package com.example.concur.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.concur.entity.Customer;
import com.example.concur.repository.JPACustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private JPACustomerRepository jpaCustomerRepository;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @KafkaListener(topics = "topicToken",groupId = "project2")
    private void getCustomerByToken(String token){
        logger.info("Receive message from CWT Successfully!");
        // decode email、expiration_timestamp from the token
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("sub").asString();
        Date expireTime = decodedJWT.getClaim("exp").asDate();
        Date curTime = new Date(System.currentTimeMillis());
        //check time expired or not
        if(expireTime.before(curTime)){
            sendTokenInfo(username);
            System.out.println("Token has been expired!");
            return;
        }
        Optional<Customer> customer = jpaCustomerRepository.findCustomerByEmail(username);
        if(customer.isPresent()){
            sendCustomerInfo(customer.get().toString());
        }else{
            sendCustomerInfo("Please fill in detailed user information first!");
        }
    }
    // Send message back to CWT
    private void sendTokenInfo(String email){
        kafkaTemplate.send("topicEmailBack",email);
        logger.info("Send email to CWT Successfully!");
    }
    // Send message to CustomerSystem
    private void sendCustomerInfo(String customer){
        kafkaTemplate.send("topicCustomer",customer);
        logger.info("Send customer information to CustomerSystem Successfully!");
    }
    public Customer getCustomerByEmail(String email){
        Optional<Customer> customer = jpaCustomerRepository.findCustomerByEmail(email);
        if(customer.isPresent()){
            return customer.get();
        }
        return null;
    }
    public Customer getCustomerByID(String id){
        Optional<Customer> customer = jpaCustomerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        return null;
    }

    public void saveCustomer(Customer customer){
        jpaCustomerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        jpaCustomerRepository.delete(customer);
    }
}
