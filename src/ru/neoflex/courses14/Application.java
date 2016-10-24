package ru.neoflex.courses14;

import ru.neoflex.courses14.menu.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private Map<String, Object> session;
    private Menu menu;

    public Application() {
        session = new HashMap<String, Object>();
    }
    public Object sessionGet(String key){
        return session.get(key);
    }
    public  void sessionPut(String key, Object obj){
        session.put(key,obj);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        menu.addObserver(this);
        menu.render(true);
        String command = getCommandString();
        menu.click(command);
    }

    private String getCommandString() {
        String command;
        while ((command = new Scanner(System.in).nextLine()).equals("")) {
            System.out.println("Ошибка: Команда не была введена.");
        }
        return command;
    }
}
