package com.example.customersystem.service;

import com.example.customersystem.entity.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    private Map<String,String> loginInfo = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        loginInfo.put("Sincere@april.biz","Sincere@april.biz123456");
        loginInfo.put("Shanna@melissa.tv","Shanna@melissa.tv123456");
        loginInfo.put("Nathan@yesenia.net","Nathan@yesenia.net123456");
        loginInfo.put("Julianne.OConner@kory.org","Julianne.OConner@kory.org123456");
        loginInfo.put("Lucio_Hettinger@annie.ca","Lucio_Hettinger@annie.ca123456");
        loginInfo.put("Karley_Dach@jasper.info","Karley_Dach@jasper.info123456");
        loginInfo.put("Telly.Hoeger@billy.biz","Telly.Hoeger@billy.biz123456");
        loginInfo.put("Sherwood@rosamond.me","Sherwood@rosamond.me123456");
        loginInfo.put("Chaim_McDermott@dana.io","Chaim_McDermott@dana.io123456");
        loginInfo.put("Rey.Padberg@karina.biz","Rey.Padberg@karina.biz123456");

        if(!loginInfo.containsKey(s)){
            throw new UsernameNotFoundException("Username doesn't exist, please contact administrator!");
        }
        return new User(s,loginInfo.get(s),new ArrayList<>());
    }
    public void sendLoginInfo(Login login){
        String username = login.getUsername();
        kafkaTemplate.send("topicUsername",username);
        logger.info("Send username to CWT Successfully!");
    }

    @KafkaListener(topics = "topicCustomer",groupId = "project2")
    public void getCustomerInfo(String message){
        logger.info("Receive message from CONCUR Successfully!");
        System.out.println(message);
    }

}
