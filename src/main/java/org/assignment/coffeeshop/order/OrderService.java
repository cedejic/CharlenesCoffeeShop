package org.assignment.coffeeshop.order;

import org.assignment.coffeeshop.loyalty.LoyaltyCard;

import java.math.BigDecimal;

public interface OrderService {

    BigDecimal charge(Order order, LoyaltyCard loyaltyCard);
    BigDecimal charge(Order order);
    void printReceipt(Order order);
}
