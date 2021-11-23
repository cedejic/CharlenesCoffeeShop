package org.assignment.coffeeshop;

import org.assignment.coffeeshop.consumable.beverage.BeverageSize;
import org.assignment.coffeeshop.consumable.beverage.Coffee;
import org.assignment.coffeeshop.consumable.beverage.OrangeJuice;
import org.assignment.coffeeshop.consumable.extra.ExtraMilk;
import org.assignment.coffeeshop.consumable.extra.FoamedMilk;
import org.assignment.coffeeshop.consumable.extra.SpecialRoastCoffee;
import org.assignment.coffeeshop.consumable.snack.BaconRoll;
import org.assignment.coffeeshop.loyalty.LoyaltyCard;
import org.assignment.coffeeshop.order.Order;
import org.assignment.coffeeshop.order.OrderService;
import org.assignment.coffeeshop.order.OrderStatus;
import org.assignment.coffeeshop.order.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assignment.coffeeshop.util.FormattingUtils.getDoubleValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoffeeShopTests {

    @Test
    @DisplayName("Test order beverage SUCCESS")
    void testOrderBeverageSuccess() {
        OrderService orderService = new OrderServiceImpl();

        Order order = new Order(new Coffee(BeverageSize.SMALL));
        assertEquals(OrderStatus.NEW, order.getStatus());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(OrderStatus.CHARGED, order.getStatus());
        assertEquals(getDoubleValue(BigDecimal.valueOf(2.5)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order beverage and two extras SUCCESS")
    void testOrderBeverageAndTwoExtrasSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.SMALL, new SpecialRoastCoffee(), new ExtraMilk()));

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(3.7)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order snack SUCCESS")
    void testOrderSnackSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new BaconRoll());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(4.5)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order beverage and snack without extra SUCCESS")
    void testOrderBeverageAndSnackWithoutExtraSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.MEDIUM),
            new BaconRoll());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(7.5)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order beverage and snack with extra SUCCESS")
    void testOrderBeverageAndSnackWithExtraSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new BaconRoll());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(7.5)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order three beverages and two snacks with several extras SUCCESS")
    void testOrderThreeBeveragesAndTwoSnacksWithSeveralExtrasSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new SpecialRoastCoffee(), new FoamedMilk()),
            new Coffee(BeverageSize.LARGE, new FoamedMilk()),
            new BaconRoll(),
            new BaconRoll());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(19.)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order three beverages and two snacks with only one extra SUCCESS")
    void testOrderThreeBeveragesAndTwoSnacksWithOnlyOneExtraSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL),
            new Coffee(BeverageSize.LARGE),
            new BaconRoll(),
            new BaconRoll());

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(18.)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order six beverages and two snacks with several extras and loyalty card SUCCESS")
    void testOrderSixBeveragesAndTwoSnacksWithSeveralExtrasAndLoyaltyCardSuccess() {
        OrderService orderService = new OrderServiceImpl();

        LoyaltyCard loyaltyCard = new LoyaltyCard();
        Order order = new Order(new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new SpecialRoastCoffee(), new FoamedMilk()),
            new Coffee(BeverageSize.LARGE, new FoamedMilk()),
            new OrangeJuice(),
            new OrangeJuice(),
            new BaconRoll(),
            new BaconRoll());

        BigDecimal result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(22.95)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order five beverages without loyalty card SUCCESS")
    void testOrderFiveBeverageWithoutLoyaltyCardSuccess() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(
            new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new FoamedMilk(), new SpecialRoastCoffee()),
            new Coffee(BeverageSize.SMALL),
            new Coffee(BeverageSize.LARGE));

        BigDecimal result = orderService.charge(order);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(16.)), getDoubleValue(result));
    }

    @Test
    @DisplayName("Test order five beverages with loyalty card SUCCESS")
    void testOrderFiveBeverageWithLoyaltyCardSuccess() {
        OrderService orderService = new OrderServiceImpl();

        Coffee fifthBeverage = new Coffee(BeverageSize.LARGE);

        LoyaltyCard loyaltyCard = new LoyaltyCard();
        Order order = new Order(
            new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new FoamedMilk(), new SpecialRoastCoffee()),
            new Coffee(BeverageSize.SMALL),
            fifthBeverage);
        assertEquals(OrderStatus.NEW, order.getStatus());

        BigDecimal result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(OrderStatus.CHARGED, order.getStatus());
        assertEquals(getDoubleValue(BigDecimal.valueOf(12.5)), getDoubleValue(result));
        assertEquals(5, loyaltyCard.getTotalBeverageCount());
        assertTrue(fifthBeverage.isBeverageFree());
    }

    @Test
    @DisplayName("Test order six beverages with loyalty card SUCCESS")
    void testOrderSixBeverageWithLoyaltyCardSuccess() {
        OrderService orderService = new OrderServiceImpl();

        Coffee fifthBeverage = new Coffee(BeverageSize.SMALL);

        LoyaltyCard loyaltyCard = new LoyaltyCard();
        Order order = new Order(
            new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new FoamedMilk(), new SpecialRoastCoffee()),
            new Coffee(BeverageSize.LARGE),
            fifthBeverage,
            new Coffee(BeverageSize.LARGE));

        BigDecimal result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(17.)), getDoubleValue(result));
        assertEquals(6, loyaltyCard.getTotalBeverageCount());
        assertTrue(fifthBeverage.isBeverageFree());
    }

    @Test
    @DisplayName("Test several orders with five beverages and loyalty card SUCCESS")
    void testSeveralOrdersWithFiveBeverageAndLoyaltyCardSuccess() {
        OrderService orderService = new OrderServiceImpl();

        LoyaltyCard loyaltyCard = new LoyaltyCard();
        Order order = new Order(
            new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new ExtraMilk()));

        BigDecimal result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(6.1)), getDoubleValue(result));
        assertEquals(2, loyaltyCard.getTotalBeverageCount());

        order = new Order(
            new Coffee(BeverageSize.MEDIUM, new ExtraMilk()),
            new Coffee(BeverageSize.SMALL, new ExtraMilk()));

        result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(6.1)), getDoubleValue(result));
        assertEquals(4, loyaltyCard.getTotalBeverageCount());

        Coffee fifthBeverage = new Coffee(BeverageSize.MEDIUM, new ExtraMilk());
        order = new Order(fifthBeverage);

        result = orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);

        assertEquals(getDoubleValue(BigDecimal.valueOf(0.3)), getDoubleValue(result));
        assertEquals(5, loyaltyCard.getTotalBeverageCount());
        assertTrue(fifthBeverage.isBeverageFree());
    }

    @Test
    @DisplayName("Test adding item to order after it was charged FAIL")
    void testAddingItemToOrderAfterItWasChargedFail() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.SMALL));
        orderService.charge(order);

        OrangeJuice orangeJuice = new OrangeJuice();
        IllegalStateException exc = Assertions.assertThrows(IllegalStateException.class,
            () -> order.addItem(orangeJuice));

        assertEquals("Order must be in status NEW to add order item", exc.getMessage());
    }

    @Test
    @DisplayName("Test adding several items to order after it was charged FAIL")
    void testAddingSeveralItemsToOrderAfterItWasChargedFail() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.SMALL),
            new OrangeJuice(),
            new Coffee(BeverageSize.MEDIUM, new SpecialRoastCoffee()));
        orderService.charge(order);

        OrangeJuice orangeJuice = new OrangeJuice();
        IllegalStateException exc = Assertions.assertThrows(IllegalStateException.class,
            () -> order.addItems(orangeJuice));

        assertEquals("Order must be in status NEW to add order items", exc.getMessage());
    }

    @Test
    @DisplayName("Test setting total order value after it was charged FAIL")
    void testSettingTotalOrderValueAfterItWasChargedFail() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.SMALL));
        orderService.charge(order);

        IllegalStateException exc = Assertions.assertThrows(IllegalStateException.class,
            () -> order.setTotal(BigDecimal.ONE));

        assertEquals("Order must be in status NEW to set order total", exc.getMessage());
    }

    @Test
    @DisplayName("Test printing order before it was charged FAIL")
    void testPrintingOrderBeforeItWasChargedFail() {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order(new Coffee(BeverageSize.SMALL));

        IllegalStateException exc = Assertions.assertThrows(IllegalStateException.class,
            () -> orderService.printReceipt(order));

        assertEquals("Receipt cannot be printed if order is not charged", exc.getMessage());
    }
}
