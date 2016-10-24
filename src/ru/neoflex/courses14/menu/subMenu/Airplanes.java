package ru.neoflex.courses14.menu.subMenu;

public class Airplanes extends SubMenu {
    public Airplanes() {
        title = "Самолеты";
    }

    @Override
    public void render(boolean bool) {
        super.render(bool);
        if (bool) {
            app.sessionPut("from", "airplanes");
        }
    }

}
