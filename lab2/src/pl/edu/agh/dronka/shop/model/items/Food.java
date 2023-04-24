package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;

import java.time.LocalDate;
import java.util.Date;

public class Food extends Item {
    private final LocalDate eatByDate;

    public Food(String name, Category category, int price, int quantity, LocalDate eatByDate) {
        super(name, category, price, quantity);
        this.eatByDate = eatByDate;
    }

    public LocalDate getEatByDate() {
        return eatByDate;
    }
}
