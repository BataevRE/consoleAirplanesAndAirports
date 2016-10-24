package ru.neoflex.courses14.menu.subMenu;

import ru.neoflex.courses14.Application;
import ru.neoflex.courses14.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class Airports extends SubMenu {
    public Airports() {
        title = "Аэропорты";
    }
    @Override
    public void render(boolean bool) {
        super.render(bool);
        if (bool) {
            app.sessionPut("from", "airports");
        }
    }
}


