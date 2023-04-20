package com.example.concur.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "geography")
public class Geography {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;

    @JsonIgnore
    @OneToOne(mappedBy = "geography")
    private Address address;

    public Geography() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Geography(String id, String lat, String lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        StringBuilder sbSpace = new StringBuilder();
        for (int i = 0; i <= 26; i++) {
            sbSpace.append(" ");
        }
        return "Geography{" + "\n" +
                sbSpace + "id='" + id + '\'' + "," + "\n" +
                sbSpace + "lat='" + lat + '\'' + "," + "\n" +
                sbSpace + "lng='" + lng + '\'' + "," + "\n" +
                sbSpace + '}';
    }
}
