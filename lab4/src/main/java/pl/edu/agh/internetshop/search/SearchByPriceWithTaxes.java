package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;

import java.math.BigDecimal;

public class SearchByPriceWithTaxes extends AbstractSearchByPrice {
    public SearchByPriceWithTaxes(BigDecimal minPrice, BigDecimal maxPrice) {
        super(minPrice, maxPrice);
    }

    @Override
    public BigDecimal getPrice(Order order) {
        return order.getPriceWithTaxes();
    }
}
