package ru.neoflex.courses14.menu.menuItem;

import ru.neoflex.courses14.services.LocationOfAirplanesService;

public class AddAirplaneToAirport extends MenuItem {
    public AddAirplaneToAirport() {
        title = "Добавить";
    }

    @Override
    public void click(String command) {
        if ((app.sessionGet("airport") != null) && (app.sessionGet("airplane") != null)) {
            new LocationOfAirplanesService().addLink((Long) app.sessionGet("airport"), (Long) app.sessionGet("airplane"));
            System.out.println("Самолет добавлен в аэропорт");
        } else {

        }

    }
}
