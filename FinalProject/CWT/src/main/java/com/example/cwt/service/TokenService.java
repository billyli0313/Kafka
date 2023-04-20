package com.example.cwt.service;

import com.example.cwt.entity.Token;
import com.example.cwt.repository.JPATokenRepository;
import com.example.cwt.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;

@Service
public class TokenService{
    @Autowired
    private JPATokenRepository jpaTokenRepository;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(TokenService.class);
    @KafkaListener(topics = "topicUsername",groupId = "project2")
    public Token getTokenByEmail(String email) {
        Token tokenTable;
        logger.info("Receive email from CustomerSystem Successfully!");
        //If token doesn't exist, creat one
        if(jpaTokenRepository.findByEmail(email) == null){
            tokenTable = createNewToken(email);
            logger.info("Create a new token: " + tokenTable.toString());
        //Token exists
        }else{
            tokenTable = jpaTokenRepository.findByEmail(email);
            //Check if date is expired
            Timestamp ts=new Timestamp(System.currentTimeMillis());
            Date CurDate=ts;
            Date future2minDate=ts;
            future2minDate.setTime(future2minDate.getTime()+ 120*1000);//Expire in 2 minutes
            Date ExpireDate = jpaTokenRepository.findByEmail(email).getExpiration_timestamp();
            //For expired token, create a new one
            if(ExpireDate.before(future2minDate) || ExpireDate.before(CurDate)){
                jpaTokenRepository.deleteById(tokenTable.getToken());
                tokenTable = createNewToken(email);
                logger.info("Create a new token: " + tokenTable.toString());
            }else{
                logger.info("Use the existing token: " + tokenTable.toString());
            }
        }
        sendToken(tokenTable.getToken());
        return tokenTable;
    }
    @KafkaListener(topics = "topicEmailBack",groupId = "project2")
    public void CreateNewTokenByEmail(String email) {
        logger.info("Receive email from CONCUR Successfully!");
        String token = createNewToken(email).getToken();
    }
    private Token createNewToken(String email){
        String token = jwtUtility.generateToken(email);
        Date timestamp = jwtUtility.getExpirationDateFromToken(token);
        Token tokenTable = new Token(email,token,timestamp);
        jpaTokenRepository.save(tokenTable);
        return tokenTable;
    }

    public void sendToken(String token){
        kafkaTemplate.send("topicToken",token);
        logger.info("Send Token to CONCUR Successfully!");
    }

}
