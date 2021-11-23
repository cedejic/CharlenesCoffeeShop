package org.assignment.coffeeshop.consumable.snack;

import java.math.BigDecimal;

public class BaconRoll implements Snack {

    @Override
    public BigDecimal getCost() {
        return BigDecimal.valueOf(4.5);
    }

    @Override
    public String getDescription() {
        return "Bacon roll";
    }
}
