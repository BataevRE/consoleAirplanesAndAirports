package ru.neoflex.courses14.menu.subMenu;

import ru.neoflex.courses14.Application;
import ru.neoflex.courses14.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class SubMenu implements Menu {
    protected Application app;
    protected String title;
    protected List<Menu> menuList = new ArrayList<Menu>();

    public void addMenu(Menu menu) {
        menuList.add(menu);
    }

    public void addObserver(Application app) {
        this.app = app;
    }

    @Override
    public void render(boolean bool) {
        System.out.println(title);
        if (bool) {
            bool = false;
            for (int i = 0; i < menuList.size(); i++) {
                System.out.print(i + 1 + ". ");
                menuList.get(i).render(bool);
            }
        }
    }

    @Override
    public void click(String command) {
        Menu menu = menuList.get(Integer.parseInt(command)-1);
        if (menu instanceof SubMenu) {
            app.setMenu(menu);
        } else {
            menu.addObserver(app);
            menu.render(false);
            menu.click(command);
        }
    }
}
