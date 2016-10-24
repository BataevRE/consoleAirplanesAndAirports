package ru.neoflex.courses14.menu.menuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.neoflex.courses14.entity.Airplane;
import ru.neoflex.courses14.services.AirplanesService;

import java.util.Random;
import java.util.Scanner;


public class Create extends MenuItem {
    private static final Logger log = LogManager.getLogger(Create.class);

    public Create(){
        title = "Создать";
    }

    public String getCommandString() {
        String command;
        while ((command = new Scanner(System.in).nextLine()).equals("")) {
            System.out.println("Ошибка: Команда не была введена.");
        }
        return command;
    }

    @Override
    public void click(String command) {
        System.out.println("Введите бортовой номер");
        String serialNumber = getCommandString();
        System.out.println("Введите модель");
        String model = getCommandString();
        System.out.println("Введите место назначения");
        String destination = getCommandString();
        System.out.println("Введите дату выпуска");
        String releaseDate = getCommandString();
        System.out.println("Введите компания");
        String operator = getCommandString();
        new AirplanesService().add(new Airplane(new Random().nextLong(), serialNumber, model, destination, releaseDate, operator));
    }
}
