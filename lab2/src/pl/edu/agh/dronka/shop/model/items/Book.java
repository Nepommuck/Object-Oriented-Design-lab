package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;

public class Book extends Item {
    private final int numberOfPages;
    private boolean hasHardcover;

    public Book() {
        super();
        this.numberOfPages = -1;
    }
    public Book(String name, Category category, int price, int quantity, int numberOfPages, boolean hasHardcover) {
        super(name, category, price, quantity);
        this.numberOfPages = numberOfPages;
        this.hasHardcover = hasHardcover;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public boolean isHardcover() {
        return hasHardcover;
    }
    public void setHandcover(boolean value) {
        this.hasHardcover = value;
    }
}
