package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

	private Order getOrderWithMockedProduct() {
		Product product = mock(Product.class);
		return new Order(product);
	}

	@Test
	public void testGetProductThroughOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Order order = new Order(expectedProduct);

		// when
		List<Product> returnedProducts = order.getProducts();

		// then
		assertEquals(returnedProducts.size(), 1);
		assertSame(expectedProduct, returnedProducts.get(0));
	}

	@Test
	public void testGetMultipleProductsThroughOrder() {
		// given
		int numberOfProducts = 10;
		List<Product> expectedProducts = new ArrayList<>();
		for (int i = 0; i < numberOfProducts; i++)
			expectedProducts.add(mock(Product.class));

		Order order = new Order(
				new ArrayList<>(expectedProducts));

		// when
		List<Product> returnedProducts = order.getProducts();

		// then
		assertEquals(returnedProducts.size(), numberOfProducts);
		for (int i = 0; i < numberOfProducts; i++)
			assertSame(expectedProducts.get(i), returnedProducts.get(i));
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPrice() throws Exception {
		// given
		int expectedProductPrice = 1000;
		Order order = getOrderWithCertainProductPrice(expectedProductPrice);

		// when
		BigDecimal actualProductPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(expectedProductPrice), actualProductPrice);
	}

	@Test
	public void testGetPriceWithGlobalDiscount() {
		// given
		Product product1 = new Product("a", BigDecimal.valueOf(20.0));
		Product product2 = new Product("b", BigDecimal.valueOf(5.50));
		Product product3 = new Product("c", BigDecimal.valueOf(15.72));
		Order order1 = new Order(Arrays.asList(product1, product2), 0.2);
		Order order2 = new Order(Arrays.asList(product1, product2, product3), 0.15);

		// when
		BigDecimal expectedValue1 = BigDecimal.valueOf(20.4);
		BigDecimal expectedValue2 = BigDecimal.valueOf(35.04);

		// then
		assertBigDecimalCompareValue(expectedValue1, order1.getPrice());
		assertBigDecimalCompareValue(expectedValue2, order2.getPrice());
	}

	@Test
	public void testGetPriceWithIndividualDiscount() {
		// given
		Product product1 = new Product("a", BigDecimal.valueOf(20.0));
		Product product2 = new Product("b", BigDecimal.valueOf(5.50));
		Product product3 = new Product("c", BigDecimal.valueOf(15.72));
		Order order1 = new Order(Arrays.asList(product1, product2));
		order1.setSingleDiscount(product1, 0.10);
		order1.setSingleDiscount(product2, 0.15);
		Order order2 = new Order(Arrays.asList(product1, product2, product3));
		order2.setSingleDiscount(product2, 0.5);
		order2.setSingleDiscount(product3, 0.05);

		// when
		BigDecimal expectedValue1 = BigDecimal.valueOf(22.68);
		BigDecimal expectedValue2 = BigDecimal.valueOf(37.68);

		// then
		assertBigDecimalCompareValue(expectedValue1, order1.getPrice());
		assertBigDecimalCompareValue(expectedValue2, order2.getPrice());
	}

	private Order getOrderWithCertainProductPrice(double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		Product product = new Product("", productPrice);
		return new Order(product);
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.01); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
																							
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
																							
	}
	@Test
	public void testPriceWithTaxesAndDiscount() {
		// given
		BigDecimal initialValue = BigDecimal.valueOf(2.20);
		Order order = new Order(new Product("", initialValue), 0.23);

		// when
		BigDecimal expectedValue = BigDecimal.valueOf(2.08);

		assertNotEquals(0, initialValue.compareTo(order.getPriceWithTaxes()));
		assertBigDecimalCompareValue(expectedValue, order.getPriceWithTaxes());
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedProduct();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithMockedProduct();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertFalse(order.isPaid());
	}
}
