package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.Storage;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.util.Random;

public class LocationOfAirplanesService implements LocationOfAirplanesServiceInterface {
    private static final Logger log = LogManager.getLogger(LocationOfAirplanesService.class);

    @Override
    public void addLink(Long airportId, Long airplaneId) {
        log.info("add locationOfAirplanes");
        LocationOfAirplanes locationOfAirplanes = new LocationOfAirplanes(airportId, airplaneId, new Random().nextLong());
        Storage.getInstance().addLocationOfAirplanes(locationOfAirplanes);
    }

    @Override
    public void removeLink(Long airportId, Long airplaneId) {
        log.info("remove locationOfAirplanes");
        for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
            if ((location.getAirportId().equals(airportId)) && (location.getAirplaneId().equals(airplaneId))) {
                Storage.getInstance().removeLocationOfAirplanes(location);
                break;
            }
        }
    }
}
