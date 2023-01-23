package menu.entries;

import java.io.Serializable;

public final class MenuEntry implements Serializable {
    public double price;
    public String name;

    public MenuEntry(double price, String name) {
        this.price = price;
        this.name = name;
    }
}
