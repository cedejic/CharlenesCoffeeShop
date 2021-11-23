package org.assignment.coffeeshop.consumable.extra;

import org.assignment.coffeeshop.consumable.Consumable;

public abstract class Extra implements Consumable {

    private boolean isFree = false;

    public void setExtraFree() {
        isFree = true;
    }
    public boolean isExtraFree() {
        return isFree;
    }
}
