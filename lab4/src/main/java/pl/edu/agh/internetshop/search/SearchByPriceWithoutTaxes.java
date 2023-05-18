package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;

import java.math.BigDecimal;

public class SearchByPriceWithoutTaxes extends AbstractSearchByPrice {
    public SearchByPriceWithoutTaxes(BigDecimal minPrice, BigDecimal maxPrice) {
        super(minPrice, maxPrice);
    }

    @Override
    public BigDecimal getPrice(Order order) {
        return order.getPrice();
    }
}
