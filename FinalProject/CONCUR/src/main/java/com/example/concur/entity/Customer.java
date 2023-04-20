package com.example.concur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "addressID")
    private String addressID;
    @Column(name = "phone")
    private String phone;
    @Column(name = "website")
    private String website;
    @Column(name = "companyID")
    private String companyID;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "addressID")
    private Address address;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "companyID")
    private Company company;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Override
    public String toString() {
        StringBuilder sbSpace = new StringBuilder();
        for (int i = 0; i <= 8; i++) {
            sbSpace.append(" ");
        }
        return "Customer{" + "\n" +
                sbSpace + "id='" + id + '\'' + "," + "\n" +
                sbSpace + "name='" + name + '\'' + "," + "\n" +
                sbSpace + "username='" + username + '\'' + "," + "\n" +
                sbSpace + "email='" + email + '\'' + "," + "\n" +
                sbSpace + "addressID='" + addressID + '\'' + "," + "\n" +
                sbSpace + address + "," + "\n" +
                sbSpace + "phone='" + phone + '\'' + "," + "\n" +
                sbSpace + "website='" + website + '\'' + "," + "\n" +
                sbSpace + "companyID='" + companyID + '\'' + "," + "\n" +
                sbSpace + company + "," + "\n" +
                '}';
    }
}
