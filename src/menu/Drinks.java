package menu;



import menu.entries.MenuEntry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Drinks {
    private static HashMap<String, List<MenuEntry>> drinks;

    public static void loadDrinks(FileInputStream file) throws IOException {
        Properties properties = new Properties();
        properties.load(file);

        try {
            for(String key: properties.stringPropertyNames()) {
                drinks.put(key, (List<MenuEntry>) properties.get(key));
            }
        } catch (ClassCastException exception) {
            drinks = new HashMap<>();
        }

    }

    public static void saveDrinks(FileOutputStream file) throws IOException {
        Properties properties = new Properties();

        for(Map.Entry<String, List<MenuEntry>> entry: drinks.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(file, null);
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

