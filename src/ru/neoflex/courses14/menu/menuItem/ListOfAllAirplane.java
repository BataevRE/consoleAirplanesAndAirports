package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.EntityNotFoundException;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.services.AirplanesService;

import java.util.Collection;


public class ListOfAllAirplane extends MenuItem {
    private static final Logger log = LogManager.getLogger(ListOfAllAirplane.class);
    public ListOfAllAirplane(){
        title = "Список всех";
    }
    @Override
    public void click(String command) {
        try {
            Collection<Airplane> airplanes = new AirplanesService().getAll();
            String formatAirplane = "%-15s %-20s %-20s %-15s %-20s %-20s%n";
            System.out.printf(formatAirplane, "Бортовой номер", "Модель", "Место назначения", "Дата выпуска", "Компания", "ID");
            int i = 0;
            for (Airplane airplane : airplanes) {
                System.out.printf(formatAirplane, ++i + ". " + airplane.getSerialNumber(), airplane.getModel(), airplane.getDestination(), airplane.getReleaseDate(), airplane.getOperator(), airplane.getAirplaneId());
            }
        } catch (EntityNotFoundException e) {
            log.error("список самолетов пуст", e);
            System.out.println("Список самолетов пуст");
        }
    }
}
