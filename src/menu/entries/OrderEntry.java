package menu.entries;

public final class OrderEntry {
    public final MenuEntry menuEntry;
    public final int quantity;
    private boolean completed = false;
    public OrderEntry(MenuEntry menuEntry, int quantity) {
        this.menuEntry = menuEntry;
        this.quantity = quantity;
    }

    public void complete() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }
}
