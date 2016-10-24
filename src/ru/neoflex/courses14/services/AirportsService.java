package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.Storage;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirportsService implements AirportsServiceInterface {
    private static final Logger log = LogManager.getLogger(AirportsService.class);

    @Override
    public List<Airport> getAll() throws EntityNotFoundException {
        List<Airport> airports = new ArrayList<Airport>();
        airports.addAll(Storage.getInstance().getAirports().values());
        if (airports.size() == 0) {
            throw new EntityNotFoundException();
        }
        return airports;
    }

    @Override
    public Airport getById(Long id) throws EntityNotFoundException {
        Airport airport = Storage.getInstance().getAirports().get(id);
        if (airport == null) {
            throw new EntityNotFoundException();
        }
        return airport;
    }

    @Override
    public List<Airport> getByCity(String city) throws EntityNotFoundException {
        List<Airport> result = new ArrayList<Airport>();
        for (Airport airport : Storage.getInstance().getAirports().values()) {
            if (airport.getCity().equals(city)) {
                result.add(airport);
            }
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public Airport getByCodeIATA(String codeIATA) throws EntityNotFoundException {
        Airport result = null;
        for (Airport airport : Storage.getInstance().getAirports().values()) {
            if (airport.getCodeIATA().equals(codeIATA)) {
                result = airport;
                break;
            }
        }
        if (result == null) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public List<Airport> getByThroughput(String throughput) throws EntityNotFoundException {
        List<Airport> result = new ArrayList<Airport>();
        for (Airport airport : Storage.getInstance().getAirports().values()) {
            if (airport.getThroughput().equals(throughput)) {
                result.add(airport);
            }
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public void add(Airport airport) {
        log.info("add Airport");
        Storage.getInstance().addAirport(airport);
    }

    @Override
    public void update(Airport airport) {
        log.info("update Airport");
        add(airport);
    }

    @Override
    public void remove(Long airportId) {
        log.info("remove Airport");
        Storage.getInstance().removeAirport(airportId);
    }

    @Override
    public List<Airplane> getAirplanesInAirport(Long airportId) throws EntityNotFoundException {
        Map<Long, Airplane> airplanes = Storage.getInstance().getAirplanes();
        List<Airplane> result = new ArrayList<Airplane>();
        for (LocationOfAirplanes location : Storage.getInstance().getLocationsOfAirplanes()) {
            if (location.getAirportId().equals(airportId)) {
                result.add(airplanes.get(location.getAirplaneId()));
            }
        }
        if (result.size() == 0) {
            throw new EntityNotFoundException();
        }
        return result;
    }
}
