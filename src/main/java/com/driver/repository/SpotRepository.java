package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driver.model.Spot;
import com.driver.model.SpotType;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{
    Spot findFirstBySpotTypeAndOccupiedAndParkingLotIdOrderByPricePerHourAsc(SpotType spotType, boolean occupied, int parkingLotId);

    Spot findFirstByOccupiedAndParkingLotIdAndNumberOfWheelsGreaterThanEqualOrderByPricePerHourAsc(
        boolean occupied, int parkingLotId, int numberOfWheels);
}
