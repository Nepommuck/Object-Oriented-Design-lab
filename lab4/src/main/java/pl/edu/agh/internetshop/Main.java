package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Main {
    public static void main(String args[]) {
        BigDecimal num = BigDecimal.valueOf(0.23);
        System.out.println(num);
        System.out.println(num.multiply(BigDecimal.valueOf(1)));

        Product product = new Product("Test", BigDecimal.valueOf(5));
        Order order = new Order(product, 0.10);

        System.out.println(order.getPrice());
        System.out.println(order.getPriceWithTaxes());
//        System.out.println(product.getPrice(0.05));
    }
}
