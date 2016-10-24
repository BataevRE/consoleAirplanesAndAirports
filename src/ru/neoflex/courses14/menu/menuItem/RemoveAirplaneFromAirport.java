package ru.neoflex.courses14.menu.menuItem;

import ru.neoflex.courses14.services.LocationOfAirplanesService;

public class RemoveAirplaneFromAirport extends MenuItem {
    public RemoveAirplaneFromAirport() {
        title = "Удалить";
    }

    @Override
    public void click(String command) {
        if ((app.sessionGet("airport") != null) && (app.sessionGet("airplane") != null)) {
            new LocationOfAirplanesService().removeLink((Long) app.sessionGet("airport"), (Long) app.sessionGet("airplane"));
            System.out.println("Самолет удален из аэропорта");
        } else {

        }
    }
}
