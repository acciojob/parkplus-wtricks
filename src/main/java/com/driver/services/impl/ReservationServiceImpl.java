package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    
    @Override
    @Transactional
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        Optional<User> users = userRepository3.findById(userId);
        if (users.isEmpty()) {
            throw new Exception("Cannot make reservation");
        }

        SpotType type = SpotType.OTHERS;
        if (numberOfWheels == 4) {
            type = SpotType.FOUR_WHEELER;
        } else if (numberOfWheels == 2) {
            type = SpotType.TWO_WHEELER;
        } 

        User user = users.get();
        Spot spot = spotRepository3
            .findFirstBySpotTypeAndOccupiedAndParkingLotIdOrderByPricePerHourAsc(type, false, parkingLotId);
        
        if (spot == null) {
            throw new Exception("Cannot make reservation");
        }
        
        spot.setOccupied(true);
        spotRepository3.save(spot);

        Reservation reservation = Reservation.builder()
                                .numberOfHours(timeInHours)
                                .user(user)
                                .spot(spot)
                                .build();

        return reservationRepository3.save(reservation);
    }
}
