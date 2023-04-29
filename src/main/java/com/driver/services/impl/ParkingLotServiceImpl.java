package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot ans = new ParkingLot();
        ans.setName(name);
        ans.setAddress(address);

        return parkingLotRepository1.save(ans);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {

        Spot newSpot = new Spot();
        if (numberOfWheels == 2) newSpot.setSpotType(SpotType.TWO_WHEELER);
        else if(numberOfWheels == 4) newSpot.setSpotType(SpotType.FOUR_WHEELER);
        else newSpot.setSpotType(SpotType.OTHERS);

        newSpot.setPricePerHour(pricePerHour);
        newSpot.setOccupied(Boolean.FALSE);

        newSpot.setParkingLot(parkingLotRepository1.findById(parkingLotId).get());

        return spotRepository1.save(newSpot);
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.delete(spotRepository1.findById(spotId).get());
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot updated = spotRepository1.findById(spotId).get();
        updated.setParkingLot(parkingLotRepository1.findById(parkingLotId).get());
        updated.setPricePerHour(pricePerHour);
        return new Spot();
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
