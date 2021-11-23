package org.assignment.coffeeshop.consumable.beverage;

import org.assignment.coffeeshop.consumable.extra.Extra;

import java.math.BigDecimal;
import java.util.List;

public class OrangeJuice extends Beverage {

    public OrangeJuice(Extra... items) {
        if (items.length > 0) {
            extras.addAll(List.of(items));
        }
    }

    @Override
    public BigDecimal getCost() {
        if (isBeverageFree()) return BigDecimal.ZERO;

        return BigDecimal.valueOf(3.95);
    }

    @Override
    public String getDescription() {
        return "Freshly squeezed orange juice";
    }
}
