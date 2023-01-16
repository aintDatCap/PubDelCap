package menu;

import java.util.List;

public class Order {
    private int table;
    private List<String> food;
    private List<String> drinks;

    public Order(int table, List<String> food, List<String> drinks) {
        this.table = table;
        this.food = food;
        this.drinks = drinks;
    }

    public double totalPrice() {
        return 0; // TODO
    }
}
