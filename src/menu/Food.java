package menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Food {
    private static HashMap<String, List<MenuEntry>> foods;

    public Food(FileInputStream file) throws IOException {
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

    public void saveDrinks(FileOutputStream file) throws IOException {
        Properties properties = new Properties();

        for(Map.Entry<String, List<MenuEntry>> entry: foods.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(file, null);
    }

    public void addCategory(String categoryName) {
        if(foods.containsKey(categoryName))
            return;
        foods.put(categoryName, new ArrayList<>());
    }

    public void addEntry(String categoryName, MenuEntry entry) {
        foods.get(categoryName).add(entry);
    }

    public static void main(String[] args) {

    }
}
