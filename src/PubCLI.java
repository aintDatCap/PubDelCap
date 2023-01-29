import menu.Drinks;
import menu.Food;
import menu.entries.MenuEntry;
import menu.entries.OrderEntry;
import menu.exceptions.TableDoesNotExistException;
import menu.exceptions.TableIsAlreadyOccupiedException;
import menu.exceptions.TableIsNotOccupiedException;

import java.util.Scanner;

public class PubCLI {
    private static final Scanner scanner = new Scanner(System.in);
    public static void foodMenu() {
        System.out.println("Quali operazioni vuoi eseguire?");
        System.out.println("1) Stampa tutti i cibi con annesse le categorie");
        System.out.println("2) Aggiungi un cibo");
        System.out.println("3) Rimuovi un cibo");
        System.out.println("0) Chiudi il menu");
        int action;
        do {

            System.out.print(">");

            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1 -> {
                    for (String key : Food.foods.keySet()) {
                        System.out.println(key);
                        for (MenuEntry entry : Food.foods.get(key)) {
                            System.out.println("\t" + entry.name + ", prezzo: " + entry.price);
                        }
                    }
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.print("A che categoria vuoi aggiungere il cibo? ");
                    String category = scanner.nextLine();
                    System.out.print("Come si chiama il cibo? ");
                    String foodName = scanner.nextLine();
                    System.out.print("Che prezzo vuoi assegnargli? ");
                    double price = scanner.nextDouble();
                    Food.addCategory(category);
                    Food.addEntry(category, new MenuEntry(price, foodName));
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("Nome del cibo da rimuovere: ");
                    String foodName = scanner.nextLine();
                    Food.removeFood(foodName);
                }
                default -> {
                    if(action != 0)
                        System.out.println("Azione non valida");
                }
            }

        } while (action != 0);
    }


    public static void drinksMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quali operazioni vuoi eseguire?");
        System.out.println("1) Stampa tutti i drinks con annesse le categorie");
        System.out.println("2) Aggiungi un drink");
        System.out.println("3) Rimuovi un drink");
        System.out.println("0) Chiudi il menu");

        int action;
        do {
            System.out.print(">");

            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1 -> {
                    for (String key : Drinks.drinks.keySet()) {
                        System.out.println(key);
                        for (MenuEntry entry : Drinks.drinks.get(key)) {
                            System.out.println("\t" + entry.name + ", prezzo: " + entry.price);
                        }
                    }
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.print("A che categoria vuoi aggiungere il drinks? ");
                    String category = scanner.nextLine();
                    System.out.print("Che drink vuoi inserire? ");
                    String drinkName = scanner.nextLine();
                    System.out.print("Che prezzo vuoi assegnargli? ");
                    double price = scanner.nextDouble();
                    Drinks.addCategory(category);
                    Drinks.addEntry(category, new MenuEntry(price, drinkName));
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("A che drink vuoi rimuovere ? ");
                    String drinkName = scanner.nextLine();
                    Drinks.removeDrink(drinkName);
                }
                default -> {
                    if(action != 0)
                        System.out.println("Azione non valida");
                }
            }
        } while (action != 0);

    }


    public static void orderMenu(Pub pub) {
        System.out.println("1) Cerca un tavolo vuoto");
        System.out.println("2) Imposta un tavolo come occupato");
        System.out.println("3) Fai un ordinazione per un tavolo");
        System.out.println("4) Completa un ordine");
        System.out.println("5) Calcola il conto di un tavolo");
        System.out.println("6) Pulisci il tavolo");
        System.out.println("0) Chiudi il menu");

        int action;
        do {

            System.out.print(">");
            try {
                action = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Il comando non è valido");
                action = 0;
            }

            switch (action) {
                case 1 -> {
                    int tableNumber = pub.findFreeTable();
                    if(tableNumber != -1)
                        System.out.println("Il tavolo numero " + tableNumber + " è vuoto");
                    else
                        System.out.println("Non ci sono tavoli liberi");
                }
                case 2 -> {
                    System.out.println("Numero del tavolo che si vuole occupare: ");

                    int tableNum = scanner.nextInt() -1;

                    try {
                        pub.occupyTable(tableNum);
                    } catch (TableIsAlreadyOccupiedException ignored){
                        System.out.println("Il tavolo numero "+(tableNum+1)+" è già stato occupato");
                    } catch (TableDoesNotExistException ignored) {
                        System.out.println("Il tavolo numero "+(tableNum+1)+" non esiste");
                    }
                }
                case 3 -> {
                    System.out.print("Numero del tavolo che sta ordinando: ");

                    int tableNum = scanner.nextInt() - 1;
                    scanner.nextLine();

                    try {
                        if(!pub.isTableOccupied(tableNum))   {
                            System.out.println("Il tavolo non è stato occupato");
                            break;
                        }
                    } catch (TableDoesNotExistException ignored) {
                        System.out.println("Il tavolo non esiste");
                        break;
                    }
                    System.out.print("Quante cose vuole ordinare il tavolo? ");
                    int orderedThingsTotal = scanner.nextInt();

                    scanner.nextLine();
                    for(int i = 0; i < orderedThingsTotal; i++) {
                        System.out.print("Cosa ordinata: ");
                        String orderedThing = scanner.nextLine();
                        MenuEntry entry = MenuEntry.fromName(orderedThing);

                        if (entry == null) {
                            System.out.println("Impossibile trovare il cibo/la bevanda specificato/a");
                        } else {

                            System.out.print("Quantità: ");
                            int quantity = scanner.nextInt();

                            if (quantity <= 0) {
                                System.out.println("La quantità deve essere positiva");
                            }

                            OrderEntry order = new OrderEntry(entry, quantity);

                            try {
                                pub.addOrder(tableNum, order);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Numero tavolo a cui stai servendo l'ordine? ");
                    int tableNum = scanner.nextInt() - 1;
                    try {
                        pub.serveOrder(tableNum);
                    } catch (TableDoesNotExistException ignored) {
                        System.out.println("Il tavolo numero " + (tableNum+1) + " non esiste");
                    } catch (TableIsNotOccupiedException ignored) {
                        System.out.println("Il tavolo non è stato occupato");
                    }
                }
                case 5 -> {
                    System.out.println("Numero tavolo che richiede l'ordine? ");
                    int tableNum = scanner.nextInt() - 1;
                    try {
                        System.out.println(pub.bill(tableNum));
                    } catch (Exception ignored) {

                    }
                }
                case 6 -> {
                    System.out.println("Che tavolo vuoi pulire? ");
                    int tableNum = scanner.nextInt() - 1;
                    try {
                        pub.clearTable(tableNum);
                    } catch (Exception ignored) {
                    }
                }
                default -> {
                    if(action != 0)
                        System.out.println("Azione non valida");
                }
            }
        } while (action != 0);
    }

    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception ignored) {

        }
    }

    public static void mainMenu() {

        Pub pub;

        System.out.println("I posti esterni sono disponibili? (S/N) ");
        String response = scanner.next();
        if(response.equals("S")) {
            pub = new Pub(true);
        } else {
            pub = new Pub(false);
        }
        clearConsole();

        int action;
        do {
            System.out.println("1) Accesso al menu dei cibi");
            System.out.println("2) Accesso al menu delle bevande");
            System.out.println("3) Accesso al menu degli ordini");
            System.out.println("4) Salva il menu");
            System.out.println("0) Termina il programma");
            System.out.print("> ");

            action = scanner.nextInt();
            clearConsole();

            switch (action) {
                case 1 -> {
                    foodMenu();
                    clearConsole();
                }
                case 2 -> {
                    drinksMenu();
                    clearConsole();
                }
                case 3 -> {
                    orderMenu(pub);
                    clearConsole();
                }
                case 4 -> {
                    try {
                        Food.saveFood("food.menu");
                        Drinks.saveDrinks("drinks.menu");
                    } catch (Exception e) {
                        System.out.println("Salvataggio dei menù non riuscito");
                    }
                }
                default -> {
                    if(action != 0)
                        System.out.println("Azione non valida");
                }
            }
        }while (action != 0);

        scanner.close();
    }
}
