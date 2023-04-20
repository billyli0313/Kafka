package com.example.cwt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "token")
public class Token {
    @Column(name = "email")
    private String email;
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "expiration_timestamp")
    private Date expiration_timestamp;

    public Token() {

    }
    public Token(String email,String token,Date expiration_timestamp){
        this.email = email;
        this.token = token;
        this.expiration_timestamp = expiration_timestamp;
    }
    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Date getExpiration_timestamp() {
        return expiration_timestamp;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpiration_timestamp(Date expiration_timestamp) {
        this.expiration_timestamp = expiration_timestamp;
    }

    @Override
    public String toString() {
        return "Token{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", expiration_timestamp=" + expiration_timestamp +
                '}';
    }
}
