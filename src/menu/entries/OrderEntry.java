package menu.entries;

public final class OrderEntry {
    public MenuEntry menuEntry;
    public int quantity;

    public OrderEntry(MenuEntry menuEntry, int quantity) {
        this.menuEntry = menuEntry;
        this.quantity = quantity;
    }
}
