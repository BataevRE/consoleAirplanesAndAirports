package ru.neoflex.courses14.services;//To change this template use File | Settings | File Templates.

public interface LocationOfAirplanesServiceInterface {
    abstract void addLink(Long airportId, Long airplaneId);

    abstract void removeLink(Long airportId, Long airplaneId);
}
