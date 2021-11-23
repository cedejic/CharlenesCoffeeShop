package org.assignment.coffeeshop.consumable.beverage;

import org.assignment.coffeeshop.consumable.extra.Extra;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Coffee extends Beverage {

    private final BeverageSize beverageSize;

    public Coffee(BeverageSize beverageSize, Extra... items) {
        this.beverageSize = Objects.requireNonNull(beverageSize);

        if (items.length > 0) {
            extras.addAll(List.of(items));
        }
    }

    @Override
    public BigDecimal getCost() {
        if (isBeverageFree()) return BigDecimal.ZERO;

        switch (beverageSize) {
            case SMALL: return BigDecimal.valueOf(2.5);
            case MEDIUM: return BigDecimal.valueOf(3);
            case LARGE: return BigDecimal.valueOf(3.5);
            default: throw new IllegalStateException("Undefined beverage size '" + beverageSize + "'");
        }
    }

    @Override
    public String getDescription() {
        switch(beverageSize) {
            case SMALL: return "Small coffee";
            case MEDIUM: return "Medium coffee";
            case LARGE: return "Large coffee";
            default: throw new IllegalStateException("Undefined beverage size '" + beverageSize + "'");
        }
    }
}
