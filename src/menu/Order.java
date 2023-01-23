package menu;

import menu.entries.MenuEntry;
import menu.entries.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long lastID = 0;
    public final long ID;
    private List<OrderEntry> entries;
    private boolean completed = false;

    public Order() {
        this.entries = new ArrayList<>();
        ID = lastID++;
    }

    public void addEntry(String name, int quantity, float price) {
        MenuEntry menuEntry = new MenuEntry(price, name);
        entries.add(new OrderEntry(menuEntry, quantity));
    }

    public void complete() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public double getTotalPrice() {
        double price = 0;
        for(OrderEntry order: entries) {
            price += order.quantity * order.menuEntry.price;
        }
        return price;
    }
}
