package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;

public class SearchBySurname implements ISearchStrategy {
    private final String surname;
    public SearchBySurname(String surname) {
        this.surname = surname.toLowerCase();
    }
    @Override
    public boolean filter(Order order) {
        if (order.getCustomer() == null)
            return false;
        return order.getCustomer().getSurname().toLowerCase().equals(this.surname);
    }
}
