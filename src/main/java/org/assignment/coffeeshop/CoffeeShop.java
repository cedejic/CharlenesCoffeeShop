package org.assignment.coffeeshop;

import org.assignment.coffeeshop.consumable.beverage.BeverageSize;
import org.assignment.coffeeshop.consumable.beverage.Coffee;
import org.assignment.coffeeshop.consumable.beverage.OrangeJuice;
import org.assignment.coffeeshop.consumable.extra.ExtraMilk;
import org.assignment.coffeeshop.consumable.extra.SpecialRoastCoffee;
import org.assignment.coffeeshop.loyalty.LoyaltyCard;
import org.assignment.coffeeshop.order.Order;
import org.assignment.coffeeshop.order.OrderService;
import org.assignment.coffeeshop.order.impl.OrderServiceImpl;

public class CoffeeShop {

    public static void main(String... args) {

        OrderService orderService = new OrderServiceImpl();
        LoyaltyCard loyaltyCard = new LoyaltyCard();

        Order order = new Order();
        order.addItems(
            new Coffee(BeverageSize.SMALL, new ExtraMilk(), new SpecialRoastCoffee()),
            new OrangeJuice()
        );

        orderService.charge(order, loyaltyCard);
        orderService.printReceipt(order);
    }
}
