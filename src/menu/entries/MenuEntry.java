package menu.entries;

import menu.Drinks;
import menu.Food;

import java.io.Serializable;

public final class MenuEntry implements Serializable {
    public double price;
    public String name;

    public MenuEntry(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public static MenuEntry fromName(String entryName) {
        MenuEntry entry = Food.searchFood(entryName);
        if (entry == null) {
            entry = Drinks.searchDrink(entryName);
        }
        return entry;
    }
}
