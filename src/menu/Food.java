package menu;

import menu.entries.MenuEntry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Food {
    public static HashMap<String, List<MenuEntry>> foods;

    public static void loadFood(FileInputStream file) throws IOException {
        Properties properties = new Properties();
        properties.load(file);
        try{
            for(String key: properties.stringPropertyNames()) {
                foods.put(key, (List<MenuEntry>) properties.get(key));
            }
        } catch (ClassCastException exception) {
            foods = new HashMap<>();
        }
    }

    public static void saveFood(FileOutputStream file) throws IOException {
        Properties properties = new Properties();
        properties.putAll(foods);
        properties.store(file, null);
    }

    public static void addCategory(String categoryName) {
        if(foods.containsKey(categoryName))
            return;
        foods.put(categoryName, new ArrayList<>());
    }

    public static void addEntry(String categoryName, MenuEntry entry) {
        foods.get(categoryName).add(entry);
    }

    public static void newMenu() {
        foods = new HashMap<>();
    }
}
