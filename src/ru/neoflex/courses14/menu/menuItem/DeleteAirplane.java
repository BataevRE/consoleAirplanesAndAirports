package ru.neoflex.courses14.menu.menuItem;

import ru.neoflex.courses14.services.AirplanesService;

public class DeleteAirplane extends MenuItem {
    public DeleteAirplane() {
        title = "Удалить";
    }

    @Override
    public void click(String command) {
        new AirplanesService().remove((Long) app.sessionGet("airplane"));
        System.out.println("Самолет удален");

    }
}