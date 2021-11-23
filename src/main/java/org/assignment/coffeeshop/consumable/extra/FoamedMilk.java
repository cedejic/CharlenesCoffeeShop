package org.assignment.coffeeshop.consumable.extra;

import java.math.BigDecimal;

public class FoamedMilk extends Extra {

    @Override
    public BigDecimal getCost() {
        if (isExtraFree()) return BigDecimal.ZERO;
        return BigDecimal.valueOf(0.5);
    }

    @Override
    public String getDescription() {
        return "Foamed milk";
    }
}
