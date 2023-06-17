package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;

    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = ParkingLot.builder()
                                .name(name)
                                .address(address)
                                .build();

        return parkingLotRepository1.save(parkingLot);
    }   

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Optional<ParkingLot> parkingLot = parkingLotRepository1.findById(parkingLotId);
        if (parkingLot.isEmpty()) {
            return null;
        }

        SpotType type = SpotType.OTHERS;
        if (numberOfWheels == 4) {
            type = SpotType.FOUR_WHEELER;
        } else if (numberOfWheels == 2) {
            type = SpotType.TWO_WHEELER;
        } 

        Spot spot = Spot.builder()
                    .parkingLot(parkingLot.get())
                    .spotType(type)
                    .pricePerHour(pricePerHour)
                    .occupied(false)
                    .build();

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Optional<Spot> spots = spotRepository1.findById(spotId);
        if (spots.isEmpty()) {
            return null;
        }

        Spot spot = spots.get();
        spot.setPricePerHour(pricePerHour);

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
