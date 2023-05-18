package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.OrderHistory;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private final OrderHistory orderHistory;
    private final ISearchStrategy searchStrategy;

    public Filter(OrderHistory orderHistory, ISearchStrategy searchStrategy) {
        this.orderHistory = orderHistory;
        this.searchStrategy = searchStrategy;
    }

    public List<Order> filterOrders() {
        List<Order> result = new ArrayList<>();
        for (Order order : orderHistory.getOrders()) {
            if (searchStrategy.filter(order))
                result.add(order);
        }
        return result;
    }
}
