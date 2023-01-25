import menu.Drinks;
import menu.Food;
import menu.Order;
import menu.entries.MenuEntry;

import java.util.Scanner;

public class Main {
    public static void foodMenu() {
        Scanner scanner = new Scanner(System.in);

        int action = 1;
        while(action != 0) {

            System.out.println("Quali operazioni vuoi eseguire?");
            System.out.println("1) Stampa tutti i cibi con annesse le categorie");
            System.out.println("2) Aggiungi un cibo");
            System.out.println("3) Rimuovi un cibo");
            System.out.println("0) Chiudi il menu");
            System.out.print(">");

            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1:
                    for(String key: Food.foods.keySet()) {
                        System.out.println(key);
                        for(MenuEntry entry: Food.foods.get(key)) {
                            System.out.println("\t"+entry.name+", prezzo: "+ entry.price);
                        }
                    }
                    System.out.print("Premere invio per continuare");
                    scanner.nextLine();
                    break;
                case 2:
                    scanner.nextLine();

                    System.out.print("A che categoria vuoi aggiungere il cibo? ");
                    String category = scanner.nextLine();
                    System.out.print("Che cibo vuoi inserire? ");
                    String foodName = scanner.nextLine();
                    System.out.print("Che prezzo vuoi assegnargli? ");
                    double price = scanner.nextDouble();
                    Food.addCategory(category);
                    Food.addEntry(category,new MenuEntry(price, foodName));
                    break;
                case 3:
                    scanner.nextLine();

                    System.out.print("A che cibo vuoi rimuovere ? ");
                    foodName = scanner.nextLine();
                    Food.removeFood(foodName);
                    break;
            }
        }
        scanner.close();
    }
    public static void drinksMenu(){
        Scanner scanner = new Scanner(System.in);

        int action = 1;
        while(action != 0) {

            System.out.println("Quali operazioni vuoi eseguire?");
            System.out.println("1) Stampa tutti i drinks con annesse le categorie");
            System.out.println("2) Aggiungi un drink");
            System.out.println("3) Rimuovi un drink");
            System.out.println("0) Chiudi il menu");
            System.out.print(">");

            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1:
                    for(String key: Drinks.drinks.keySet()) {
                        System.out.println(key);
                        for(MenuEntry entry: Drinks.drinks.get(key)) {
                            System.out.println("\t"+entry.name+", prezzo: "+ entry.price);
                        }
                    }
                    System.out.print("Premere invio per continuare");
                    scanner.nextLine();
                    break;
                case 2:
                    scanner.nextLine();

                    System.out.print("A che categoria vuoi aggiungere il drinks? ");
                    String category = scanner.nextLine();
                    System.out.print("Che drink vuoi inserire? ");
                    String drinksName = scanner.nextLine();
                    System.out.print("Che prezzo vuoi assegnargli? ");
                    double price = scanner.nextDouble();
                    Drinks.addCategory(category);
                    Drinks.addEntry(category,new MenuEntry(price, drinksName));
                    break;
                case 3:
                    scanner.nextLine();

                    System.out.print("A che drink vuoi rimuovere ? ");
                    drinksName = scanner.nextLine();
                    Food.removeFood(drinksName);
                    break;
            }
        }
        scanner.close();
    }

    public static void orderMenu(Pub pub) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) Cerca un tavolo vuoto");
        System.out.println("2) Fai un ordinazione per un tavolo");
        System.out.println("3) Completa un ordine");
        System.out.println("4) Calcola il conto di un tavolo");
        System.out.println("5) Pulisci il tavolo");
        System.out.print(">");
        int action = 1;
        while(action != 0) {
            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1:
                    System.out.println(pub.findFreeTable());
                    break;
                case 2:
                    //da modificare addOrder
                    break;
                case 3:
                    Order order = new Order();
                    System.out.println("Numero tavolo a cui stai servendo l'ordine? ");
                    int tableNum = scanner.nextInt();
                    try{pub.serveOrder(tableNum,order.ID);}
                    catch (Exception e){}
                    break;
                case 4:
                    System.out.println("Numero tavolo che richiede l'ordine? ");
                    tableNum = scanner.nextInt();
                    try{System.out.println(pub.bill(tableNum));}
                    catch (Exception e){}
                    break;
                case 5:
                    System.out.println("Che tavolo vuoi pulire? ");
                    tableNum = scanner.nextInt();
                    try{pub.clearTable(tableNum);}catch (Exception e){}
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Food.loadFood("food.menu");
        Drinks.loadDrinks("drinks.menu");

        Pub pub = new Pub(false);

        orderMenu(pub);
        foodMenu();
        drinksMenu();
        try {
            Food.saveFood("food.menu");
            Drinks.saveDrinks("drinks.menu");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
