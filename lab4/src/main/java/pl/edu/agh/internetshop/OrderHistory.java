package pl.edu.agh.internetshop;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
