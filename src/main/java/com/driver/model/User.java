package com.driver.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String phoneNumber;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList;

    public User() {} 

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String s) {
        password = s;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String n) {
        phoneNumber = n;
    }

    public void setId(int id) {
        this.id = id;
    }
}
