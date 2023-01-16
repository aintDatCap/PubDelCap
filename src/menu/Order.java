package menu;

import menu.entries.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderEntry> orders;
    private boolean completed = false;

    public Order() {
        this.orders = new ArrayList<>();
    }

    public void complete() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public double getTotalPrice() {
        double price = 0;
        for(OrderEntry order:orders) {
            price += order.quantity * order.menuEntry.price;
        }
        return price;
    }
}
