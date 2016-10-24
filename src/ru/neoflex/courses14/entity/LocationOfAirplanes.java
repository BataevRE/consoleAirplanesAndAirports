package ru.neoflex.courses14.entity;//To change this template use File | Settings | File Templates.


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationOfAirplanes {
    private static final Logger log = LogManager.getLogger(LocationOfAirplanes.class);
    private Long airportId;
    private Long airplaneId;
    private Long id;

    public LocationOfAirplanes() {
    }

    public LocationOfAirplanes(Long airportId, Long airplaneId, Long id) {
        this.setAirportId(airportId);
        this.setAirplaneId(airplaneId);
        this.setId(id);
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        checkField(airportId);
        this.airportId = airportId;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        checkField(airplaneId);
        this.airplaneId = airplaneId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        checkField(id);
        this.id = id;
    }

    private void checkField(Long field) {
        if (field == null) {
            log.error("поле == null");
            throw new IllegalArgumentException();
        }
    }
}
