package menu;



import menu.entries.MenuEntry;

import java.io.*;
import java.util.*;

public class Drinks {
    private static HashMap<String, List<MenuEntry>> drinks;

    public static void loadDrinks(String fileName) throws IOException {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            drinks = (HashMap<String, List<MenuEntry>>) file.readObject();
            file.close();
        } catch (Exception exception) {
            Drinks.newMenu();
        }

    }

    public static void saveDrinks(String fileName) throws IOException {
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
        file.writeObject(drinks);
        file.close();
    }

    public static void addCategory(String categoryName) {
        if(drinks.containsKey(categoryName))
            return;
        drinks.put(categoryName, new ArrayList<>());
    }

    public static void addEntry(String categoryName, MenuEntry entry) {
        drinks.get(categoryName).add(entry);
    }

    public static void newMenu() {
        drinks = new HashMap<>();
    }
}

