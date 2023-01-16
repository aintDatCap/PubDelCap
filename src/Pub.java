import menu.Order;
import menu.exceptions.TableDoesNotExistException;
import menu.exceptions.TableIsAlreadyOccupiedException;
import menu.exceptions.TableIsNotOccupiedException;

import java.util.ArrayList;
import java.util.List;



public class Pub {
    private final boolean[] tables; // if the table is occupied, then it is true
    private List<Order>[] orders;


    public Pub(boolean isSummer) {
        int totalTables = 25;
        if(isSummer)
            totalTables +=15;
        tables = new boolean[totalTables];
        orders = new ArrayList[totalTables];
    }

    public boolean isTableOccupied(int tableNumber) throws TableDoesNotExistException {
        try{
            return tables[tableNumber];
        } catch (IndexOutOfBoundsException exception){
            throw new TableDoesNotExistException();
        }
    }

    public void occupyTable(int tableNumber) throws TableDoesNotExistException, TableIsAlreadyOccupiedException {
        try {
            if(tables[tableNumber])
                throw new TableIsAlreadyOccupiedException();
            tables[tableNumber] = true;

        } catch (IndexOutOfBoundsException exception) {
            throw new TableDoesNotExistException();
        }
    }

    public void clearTable(int tableNumber) throws TableDoesNotExistException {
        try {
            tables[tableNumber] = false;
            orders[tableNumber].clear();
        } catch (IndexOutOfBoundsException exception) {
            throw new TableDoesNotExistException();
        }
    }

    public void addOrder(int tableNumber,Order order) throws TableDoesNotExistException, TableIsNotOccupiedException {
        try {
            if(!tables[tableNumber])
                throw new TableIsNotOccupiedException();
            orders[tableNumber].add(order);
        } catch (IndexOutOfBoundsException exception) {
            throw new TableDoesNotExistException();
        }
    }

    public void serveOrder(int tableNumber, long ID) throws TableDoesNotExistException, TableIsNotOccupiedException {
        try {
            if (!tables[tableNumber])
                throw new TableIsNotOccupiedException();
            for(Order order: orders[tableNumber]) {
                if(order.ID == ID) {
                    order.complete();
                    return;
                }
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new TableDoesNotExistException();
        }
    }
    public double bill(int tableNumber) throws TableDoesNotExistException, TableIsNotOccupiedException {
        try {
            double price = 0;
            if (!tables[tableNumber])
                throw new TableIsNotOccupiedException();
            for(Order order: orders[tableNumber]) {
                price += order.getTotalPrice();
            }
            return price;
        } catch (IndexOutOfBoundsException exception) {
            throw new TableDoesNotExistException();
        }
    }
}
