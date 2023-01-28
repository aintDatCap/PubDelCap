package menu;

import menu.entries.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public class TableOrderings {
    private static long lastID = 0;
    public final long ID;
    private final List<OrderEntry> orders;

    public TableOrderings() {
        this.orders = new ArrayList<>();
        ID = lastID++;
    }

    public void addOrder(OrderEntry order) {
        orders.add(order);
    }

    public double getTotalPrice() {
        double price = 0;
        for (OrderEntry order : orders) {
            if (order.isCompleted())
                price += order.quantity * order.menuEntry.price;
        }
        return price;
    }

    public OrderEntry lastOrder() {
        return orders.get(orders.size() - 1);
    }
}
