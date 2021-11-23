package org.assignment.coffeeshop.consumable.extra;

import java.math.BigDecimal;

public class ExtraMilk extends Extra {

    @Override
    public BigDecimal getCost() {
        if (isExtraFree()) return BigDecimal.ZERO;
        return BigDecimal.valueOf(0.3);
    }

    @Override
    public String getDescription() {
        return "Extra milk";
    }
}
