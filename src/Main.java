import menu.Drinks;
import menu.Food;

public class Main {


    public static void main(String[] args) {
        Food.loadFood("food.menu");
        Drinks.loadDrinks("drinks.menu");

        PubCLI.mainMenu();

        try {
            Food.saveFood("food.menu");
            Drinks.saveDrinks("drinks.menu");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}


