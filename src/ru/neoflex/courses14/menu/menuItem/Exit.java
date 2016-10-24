package ru.neoflex.courses14.menu.menuItem;

public class Exit extends MenuItem {
    public Exit() {
        title="Выход";
    }

    @Override
    public void click(String command) {
        System.exit(0);
    }
}
