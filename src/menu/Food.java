package menu;

import menu.entries.MenuEntry;
import menu.entries.OrderEntry;

import java.io.*;
import java.util.*;

public class Food {
    public static HashMap<String, List<MenuEntry>> foods;

    public static void loadFood(String fileName) {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(fileName));
            foods = (HashMap<String, List<MenuEntry>>) file.readObject();
            file.close();
        } catch (Exception exception) {
            Food.newMenu();
        }
    }

    public static void saveFood(String fileName) throws IOException {
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
        file.writeObject(foods);
        file.close();
    }

    public static void addCategory(String categoryName) {
        if(foods.containsKey(categoryName))
            return;
        foods.put(categoryName, new ArrayList<>());
    }

    public static void addEntry(String categoryName, MenuEntry entry) {
        foods.get(categoryName).add(entry);
    }

    public static void removeFood(String foodName) {
        for(String key: foods.keySet()) {
            foods.get(key).removeIf(entry -> entry.name.equals(foodName));
        }
    }
    public static void newMenu() {
        foods = new HashMap<>();
    }

    public static MenuEntry searchFood(String foodName) {
        for(String key: foods.keySet()) {
            for(MenuEntry entry: foods.get(key)) {
                if(entry.name.equals(foodName))
                    return entry;
            }
        }
        return null;
    }
}
