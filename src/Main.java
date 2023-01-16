import menu.Drinks;
import menu.Food;
import menu.Order;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pub pub = new Pub(false);

        try {
            List<String> food = Arrays.asList(Food.snack[1], Food.snack[2]);
            List<String> drinks = Arrays.asList(Drinks.beers[1], Drinks.beers[3]);
            Order order = new Order(1, food, drinks);

            pub.occupyTable(1);
            pub.addOrder(1, order);

            System.out.println(pub);
        } catch (Exception e) {

        }
    }
}
