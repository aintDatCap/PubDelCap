package menu;

import menu.entries.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int table;
    private List<OrderEntry> orders;

    public Order(int table) {
        this.table = table;
        this.orders = new ArrayList<>();
    }

    public double getTotalPrice() {
        double price = 0;
        for(OrderEntry order:orders) {
            price += order.quantity * order.menuEntry.price;
        }
        return price;
    }
}
