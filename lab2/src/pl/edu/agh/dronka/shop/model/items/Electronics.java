package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;

public class Electronics extends Item {
    private boolean isMobile;
    private boolean hasWarranty;

    public Electronics() {
        super();
    }
    public Electronics(String name, Category category, int price, int quantity, boolean isMobile, boolean hasWarranty) {
        super(name, category, price, quantity);
        this.isMobile = isMobile;
        this.hasWarranty = hasWarranty;
    }

    public boolean isMobile() {
        return isMobile;
    }
    public void setMobile(boolean value) {
        this.isMobile = value;
    }
    public boolean hasWarranty() {
        return hasWarranty;
    }
    public void setWarranty(boolean value) {
        this.hasWarranty = value;
    }
}
