package com.driver.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    private int pricePerHour;

    private boolean occupied;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList;

    @ManyToOne
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkingLot;

    public Spot() {}

    public SpotType getSpotType() {
        return spotType;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public int getId() {
        return id;
    }

    public boolean getOccupied() {
        return this.occupied;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }
}
