package com.example.concur.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "street")
    private String street;
    @Column(name = "suite")
    private String suite;
    @Column(name = "city")
    private String city;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "geoID")
    private String geoID;
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "geoID")
    private Geography geography;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Address() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public Geography getGeography() {return geography;}

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGeoID() {
        return geoID;
    }

    public void setGeoID(String geoID) {
        this.geoID = geoID;
    }

    public void setGeography(Geography geography) {
        this.geography = geography;
    }

    public Address(String id, String street, String suite, String city, String zipcode, String geoID, Geography geography) {
        this.id = id;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geoID = geoID;
        this.geography = geography;
    }

    @Override
    public String toString() {
        StringBuilder sbSpace = new StringBuilder();
        for (int i = 0; i <= 16; i++) {
            sbSpace.append(" ");
        }
        return "Address{" + "\n" +
                sbSpace + "id='" + id + '\'' + "," + "\n" +
                sbSpace + "street='" + street + '\'' + "," + "\n" +
                sbSpace + "suite='" + suite + '\'' + "," + "\n" +
                sbSpace + "city='" + city + '\'' + "," + "\n" +
                sbSpace + "zipcode='" + zipcode + '\'' + "," + "\n" +
                sbSpace + "geoID='" + geoID + '\'' + "," + "\n" +
                sbSpace + geography + "," + "\n" +
                sbSpace + '}';
    }
}
