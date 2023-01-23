import menu.Drinks;
import menu.Food;

public class Main {
    public static void foodMenu() {
        System.out.println("Quali operazioni vuoi eseguire?");
        System.out.println("1) Stampa tutti i cibi con annesse le categorie");
        System.out.println("2) Aggiungi un cibo");
        System.out.println("2) Rimuovi un cibo");
    }

    public static void main(String[] args) {
        Food.loadFood("food.menu");
        Drinks.loadDrinks("drinks.menu");



        try {
            Food.saveFood("food.menu");
            Drinks.saveDrinks("drinks.menu");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
