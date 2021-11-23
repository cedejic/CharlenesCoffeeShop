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
import org.assignment.coffeeshop.printer.LoggerService;
import org.assignment.coffeeshop.printer.impl.LoggerServiceImpl;

public class CoffeeShop {

    public static void main(String... args) {

        OrderService orderService = new OrderServiceImpl();
        LoggerService orderLoggerService = new LoggerServiceImpl();
        LoyaltyCard loyaltyCard = new LoyaltyCard();

        Order order = new Order();
        order.addItems(
            new Coffee(BeverageSize.SMALL, new ExtraMilk(), new SpecialRoastCoffee()),
            new OrangeJuice()
        );

        orderService.charge(order, loyaltyCard);
        orderLoggerService.print(order);
    }
}
