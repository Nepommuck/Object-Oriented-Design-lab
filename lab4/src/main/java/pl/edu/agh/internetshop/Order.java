package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.*;


public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> products;
    private final Map<Product, Double> discounts;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private final User customer;

    public Order(Product product) {
        this(product, 0.0, null);
    }
    public Order(Product product, User customer) {
        this(product, 0.0, customer);
    }
    public Order(List<Product> products) {
        this(products, (User) null);
    }
    public Order(List<Product> products, User customer) {
        this(products, 0.0, customer);
    }
    public Order(Product product, Double discountValue) {
        this(product, discountValue, null);
    }
    public Order(Product product, Double discountValue, User customer) {
        this(Collections.singletonList(product), discountValue, customer);
    }
    public Order(List<Product> products, Double discountValue) {
        this(products, discountValue, null);
    }
    public Order(List<Product> products, Double discountValue, User customer) {
        if (products == null)
            throw new IllegalArgumentException("Products cannot be null");
        this.products = products;
        id = UUID.randomUUID();
        paid = false;
        this.discounts = createDiscountsMap(products, discountValue);
        this.customer = customer;
    }

    private static Map<Product, Double> createDiscountsMap(List<Product> products, Double discountValue) {
        Map<Product, Double> discounts = new HashMap<>();
        for (Product product : products)
            discounts.put(product, discountValue);
        return discounts;
    }

    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPrice() {
        BigDecimal result = BigDecimal.valueOf(0.00);
        for (Product product : products) {
            result = result.add(
//                    product.getPrice());
                    product.getPrice(discounts.get(product)));
        }
        return result;
    }

    public BigDecimal getPriceWithTaxes() {
        return getPrice().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccesful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccesful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public void setSingleDiscount(Product product, double discountValue) {
        if (discountValue < 0 || discountValue > 1)
            throw new IllegalArgumentException("DiscountValue must be between 0 and 1.");
        discounts.put(product, discountValue);
    }

    public void setGlobalDiscount(double discountValue) {
        for (Product product : products)
            setSingleDiscount(product, discountValue);
    }

    public User getCustomer() {
        return customer;
    }
}
