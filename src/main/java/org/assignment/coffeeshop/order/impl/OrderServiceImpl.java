package org.assignment.coffeeshop.order.impl;

import org.assignment.coffeeshop.consumable.Consumable;
import org.assignment.coffeeshop.consumable.beverage.Beverage;
import org.assignment.coffeeshop.consumable.extra.Extra;
import org.assignment.coffeeshop.loyalty.LoyaltyCard;
import org.assignment.coffeeshop.order.Order;
import org.assignment.coffeeshop.order.OrderService;
import org.assignment.coffeeshop.order.OrderStatus;
import org.assignment.coffeeshop.printer.LoggerService;
import org.assignment.coffeeshop.printer.impl.LoggerServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final LoggerService loggerService = new LoggerServiceImpl();

    @Override
    public BigDecimal charge(Order order, LoyaltyCard loyaltyCard) {
        BigDecimal total = BigDecimal.ZERO;
        List<Consumable> items = order.getItems();

        checkForFreeExtras(items);

        for (Consumable consumable: order.getItems()) {
            if (consumable instanceof Beverage) {
                Beverage beverage = (Beverage) consumable;
                checkForFreeBeverage(loyaltyCard, beverage);

                total = total.add(beverage.getExtras().stream()
                    .map(Extra::getCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            }
            total = total.add(consumable.getCost());
        }
        order.setTotal(total);
        return total;
    }

    private void checkForFreeBeverage(LoyaltyCard loyaltyCard, Beverage beverage) {
        loyaltyCard.addBeverage();
        if (loyaltyCard.isEligibleForFreeBeverage()) {
            beverage.setBeverageFree();
        }
    }

    private void checkForFreeExtras(List<Consumable> items) {
        Map<Boolean, List<Consumable>> map = items.stream().collect(Collectors.partitioningBy(Beverage.class::isInstance));
        List<Consumable> beverages = map.get(true);
        List<Consumable> snacks = map.get(false);
        long freeExtrasCount = Math.min(beverages.size(), snacks.size());

        beverages.stream()
            .map(Beverage.class::cast)
            .flatMap(beverage -> beverage.getExtras().stream())
            .limit(freeExtrasCount)
            .forEach(Extra::setExtraFree);
    }

    @Override
    public BigDecimal charge(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        List<Consumable> items = order.getItems();

        checkForFreeExtras(items);

        for (Consumable consumable: items) {
            if (consumable instanceof Beverage) {
                Beverage beverage = (Beverage) consumable;
                total = total.add(beverage.getExtras().stream()
                    .map(Extra::getCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            }

            total = total.add(consumable.getCost());
        }
        order.setTotal(total);
        return total;
    }

    @Override
    public void printReceipt(Order order) {
        if (!OrderStatus.CHARGED.equals(order.getStatus()))
            throw new IllegalStateException("Receipt cannot be printed if order is not charged");
        loggerService.print(order);
    }
}
