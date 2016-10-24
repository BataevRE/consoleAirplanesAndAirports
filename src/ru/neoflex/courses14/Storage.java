package ru.neoflex.courses14;//To change this template use File | Settings | File Templates.

import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.entity.Airport;
import ru.neoflex.courses14.entity.LocationOfAirplanes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage {
    private static Storage instance;
    private HashMap<Long, Airport> airports;
    private HashMap<Long, Airplane> airplanes;
    private HashSet<LocationOfAirplanes> locationsOfAirplanes;

    private Storage() {
        airports = new HashMap<Long, Airport>();
        airplanes = new HashMap<Long, Airplane>();
        locationsOfAirplanes = new HashSet<LocationOfAirplanes>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addAirport(Airport airport) {
        this.airports.put(airport.getAirportId(), airport);
    }

    public void addAirplane(Airplane airplane) {
        this.airplanes.put(airplane.getAirplaneId(), airplane);
    }

    public void addLocationOfAirplanes(LocationOfAirplanes locationOfAirplanes) {
        this.locationsOfAirplanes.add(locationOfAirplanes);
    }

    public Map<Long, Airport> getAirports() {
        return (Map<Long, Airport>) airports.clone();
    }

    public Map<Long, Airplane> getAirplanes() {
        return (Map<Long, Airplane>) airplanes.clone();
    }

    public Set<LocationOfAirplanes> getLocationsOfAirplanes() {
        return (Set<LocationOfAirplanes>) locationsOfAirplanes.clone();
    }

    public void removeAirport(Long id) {
        airports.remove(id);
    }

    public void removeAirplane(Long id) {
        airplanes.remove(id);
    }

    public void removeLocationOfAirplanes(LocationOfAirplanes location) {
        locationsOfAirplanes.remove(location);
    }
}
