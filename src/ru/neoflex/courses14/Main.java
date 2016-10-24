package ru.neoflex.courses14;


import ru.neoflex.courses14.menu.menuItem.*;
import ru.neoflex.courses14.menu.subMenu.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> list = new ArrayList<String>();
    static int i = 0;

    public static void main(String[] args) {
        new BootStrap().run();
//        OldMenu menu = new OldMenu();


        SubMenu menuBegin = new Begin();
        SubMenu airplanes = new Airplanes();
        SubMenu find = new Find();
        SubMenu findById = new FindById();
        SubMenu airplaneMenu = new AirplaneMenu();


        airplaneMenu.addMenu(new DeleteAirplane());
        airplaneMenu.addMenu(new UpdateAirplane());
        airplaneMenu.addMenu(new AddAirplaneToAirport());
        airplaneMenu.addMenu(new RemoveAirplaneFromAirport());

        findById.addMenu(airplaneMenu);

        find.addMenu(new FindByDestination());
        find.addMenu(findById);

        airplanes.addMenu(new Create());
        airplanes.addMenu(new ListOfAllAirplane());
        airplanes.addMenu(find);

        menuBegin.addMenu(new Exit());
        menuBegin.addMenu(airplanes);
        menuBegin.addMenu(new Airports());

        Application application = new Application();
        application.setMenu(menuBegin);
        while (true) {
//            menu.begin();
            application.run();
        }

    }


}




