package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;

public interface ISearchStrategy {
    public boolean filter(Order order);
}
