package menu;



import menu.entries.MenuEntry;

import java.io.*;
import java.util.*;

public class Drinks {
    public static HashMap<String, List<MenuEntry>> drinks;

    public static void loadDrinks(String fileName) {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            drinks = (HashMap<String, List<MenuEntry>>) file.readObject();
            file.close();
        } catch (Exception exception) {
            System.out.println("Unable to open drinks menu at " + fileName);
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

    public static void removeCategory(String categoryName) {
        drinks.remove(categoryName);
    }

    public static void removeDrink(String drinkName) {
        for(String key: drinks.keySet()) {
            if(drinks.get(key).removeIf(entry -> entry.name.equals(drinkName)))
                return;
        }
    }

    public static MenuEntry searchDrink(String drinkName) {
        for(String key: drinks.keySet()) {
            for(MenuEntry entry: drinks.get(key)) {
                if(entry.name.equals(drinkName))
                    return entry;
            }
        }
        return null;
    }

    public static void newMenu() {
        drinks = new HashMap<>();
    }
}

