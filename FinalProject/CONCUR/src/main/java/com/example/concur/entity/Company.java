package com.example.concur.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "catchPhrase")
    private String catchPhrase;
    @Column(name = "bs")
    private String bs;

    @JsonIgnore
    @OneToOne(mappedBy = "company")
    private Customer customer;

    public Company() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company(String id, String name, String catchPhrase, String bs) {
        this.id = id;
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    @Override
    public String toString() {
        StringBuilder sbSpace = new StringBuilder();
        for (int i = 0; i <= 16; i++) {
            sbSpace.append(" ");
        }
        return "Company{" + "\n" +
                sbSpace + "id='" + id + '\'' + "," + "\n" +
                sbSpace + "name='" + name + '\'' + "," + "\n" +
                sbSpace + "catchPhrase='" + catchPhrase + '\'' + "," + "\n" +
                sbSpace + "bs='" + bs + '\'' + "," + "\n" +
                sbSpace + '}';
    }
}
