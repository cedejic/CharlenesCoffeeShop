package org.assignment.coffeeshop.consumable.extra;

import java.math.BigDecimal;

public class SpecialRoastCoffee extends Extra {

    @Override
    public BigDecimal getCost() {
        if (isExtraFree()) return BigDecimal.ZERO;
        return BigDecimal.valueOf(0.9);
    }

    @Override
    public String getDescription() {
        return "Special roast coffee";
    }
}
