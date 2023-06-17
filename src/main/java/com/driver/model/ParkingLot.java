package com.driver.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "parkingLot")
    private List<Spot> spotList;

    public ParkingLot() {}

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }
}
