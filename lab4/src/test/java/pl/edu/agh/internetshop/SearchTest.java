package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.search.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    @Test
    public void testSearchBySurname() {
        //given
        Product product1 = new Product("p1", BigDecimal.valueOf(17.76));
        Product product2 = new Product("p2", BigDecimal.valueOf(0.02));

        User person1 = new User("George", "Washington", "gw@us.us");
        User person2 = new User("King", "of the United Kingdom", "his_majesty@uk.uk");

        OrderHistory orderHistory = new OrderHistory();
        Order order1 = new Order(Arrays.asList(product1, product1, product2), person1);
        Order order2 = new Order(product1, person1);

        orderHistory.addOrder(new Order(product2));
        orderHistory.addOrder(new Order(product2, person2));
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);

        // when
        ISearchStrategy searchStrategy = new SearchBySurname("Washington");
        Filter filter = new Filter(orderHistory, searchStrategy);

        // then
        List<Order> result = filter.filterOrders();

        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    public void testSearchByProductName() {
        //given
        Product whiskey = new Product("Whiskey", BigDecimal.valueOf(17.76));
        Product beer = new Product("Beer", BigDecimal.valueOf(0.27));
        Product liquor = new Product("Liquor", BigDecimal.valueOf(1.22));

        OrderHistory orderHistory = new OrderHistory();
        Order order1 = new Order(Arrays.asList(liquor, beer, whiskey, liquor));
        Order order2 = new Order(liquor);

        orderHistory.addOrder(new Order(beer));
        orderHistory.addOrder(new Order(Arrays.asList(whiskey, beer, beer)));
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);

        // when
        ISearchStrategy searchStrategy = new SearchByProductName("liquor");
        Filter filter = new Filter(orderHistory, searchStrategy);

        // then
        List<Order> result = filter.filterOrders();

        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    public void testSearchByPriceWithoutTaxes() {
        //given
        Product whiskey = new Product("Whiskey", BigDecimal.valueOf(17.76));
        Product beer = new Product("Beer", BigDecimal.valueOf(0.27));
        Product liquor = new Product("Liquor", BigDecimal.valueOf(1.22));

        OrderHistory orderHistory = new OrderHistory();
        Order order1 = new Order(Arrays.asList(liquor, liquor, liquor));
        Order order2 = new Order(whiskey);

        orderHistory.addOrder(new Order(beer));
        orderHistory.addOrder(new Order(Arrays.asList(whiskey, whiskey)));
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);

        // when
        ISearchStrategy searchStrategy = new SearchByPriceWithoutTaxes(
                BigDecimal.valueOf(3.65), BigDecimal.valueOf(17.80));
        Filter filter = new Filter(orderHistory, searchStrategy);

        // then
        List<Order> result = filter.filterOrders();

        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }

    @Test
    public void testSearchByPriceWithTaxes() {
        //given
        Product whiskey = new Product("Whiskey", BigDecimal.valueOf(17.76));
        Product beer = new Product("Beer", BigDecimal.valueOf(0.27));
        Product liquor = new Product("Liquor", BigDecimal.valueOf(1.22));

        OrderHistory orderHistory = new OrderHistory();
        Order order1 = new Order(Arrays.asList(liquor, liquor, liquor));
        Order order2 = new Order(whiskey);

        orderHistory.addOrder(new Order(beer));
        orderHistory.addOrder(new Order(Arrays.asList(whiskey, whiskey)));
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);

        // when
        ISearchStrategy searchStrategy = new SearchByPriceWithTaxes(
                BigDecimal.valueOf(3.65), BigDecimal.valueOf(17.80));
        Filter filter = new Filter(orderHistory, searchStrategy);

        // then
        List<Order> result = filter.filterOrders();

        assertEquals(1, result.size());
        assertTrue(result.contains(order1));
    }

    @Test
    public void testSearchWithMultipleStrategies() {
        //given
        Product whiskey = new Product("Whiskey", BigDecimal.valueOf(17.76));
        Product beer = new Product("Beer", BigDecimal.valueOf(0.27));
        Product liquor = new Product("Liquor", BigDecimal.valueOf(1.22));

        User person1 = new User("George", "Washington", "gw@us.us");
        User person2 = new User("King", "of the United Kingdom", "his_majesty@uk.uk");

        OrderHistory orderHistory = new OrderHistory();
        Order order = new Order(Arrays.asList(whiskey, beer), person1);

        orderHistory.addOrder(new Order(whiskey, person1));
        orderHistory.addOrder(new Order(Arrays.asList(beer, beer, liquor), person1));
        orderHistory.addOrder(new Order(beer, person2));
        orderHistory.addOrder(new Order(Arrays.asList(whiskey, beer), person2));
        orderHistory.addOrder(order);

        // when
        ISearchStrategy searchStrategy = new SearchWithMultipleStrategies(Arrays.asList(
                new SearchByProductName("beer"),
                new SearchBySurname("Washington"),
                new SearchByPriceWithTaxes(BigDecimal.valueOf(18.50), BigDecimal.valueOf(150.00))
        ));
        Filter filter = new Filter(orderHistory, searchStrategy);

        // then
        List<Order> result = filter.filterOrders();

        assertEquals(1, result.size());
        assertTrue(result.contains(order));
    }
}
