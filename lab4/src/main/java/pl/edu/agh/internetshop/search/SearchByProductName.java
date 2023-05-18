package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

public class SearchByProductName implements ISearchStrategy {
    private final String productName;

    public SearchByProductName(String productName) {
        this.productName = productName.toLowerCase();
    }

    @Override
    public boolean filter(Order order) {
        for (Product product : order.getProducts())
            if (product.getName().toLowerCase().equals(this.productName))
                return true;
        return false;
    }
}
