import menu.Order;
import menu.exceptions.TableDoesNotExistException;
import menu.exceptions.TableIsAlreadyOccupiedException;

import java.util.List;

enum TableStatus {
    OrderInProgress,
    OrderCompleted,
    Finished,
    Paid,
}

public class Pub {
    private int totalTables = 25;
    private final boolean[] tables; // if the table is occupied, then it is true
    private List<Order>[] orders;
    private TableStatus[] status;

    public Pub(int totalTables, boolean isSummer) {
        if(isSummer)
            totalTables += 15;
        this.totalTables = totalTables;
        tables = new boolean[totalTables];
        status = new TableStatus[totalTables];
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
            status[tableNumber] = TableStatus.OrderInProgress;
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
}
