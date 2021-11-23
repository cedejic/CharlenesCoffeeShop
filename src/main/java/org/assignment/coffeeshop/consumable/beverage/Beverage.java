package org.assignment.coffeeshop.consumable.beverage;

import org.assignment.coffeeshop.consumable.Consumable;
import org.assignment.coffeeshop.consumable.extra.Extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Beverage implements Consumable {

    private boolean isFree = false;
    protected List<Extra> extras = new ArrayList<>();

    public void addExtra(Extra newExtra) {
        extras.add(newExtra);
    }

    public void addExtras(Extra... newExtras) {
        extras.addAll(List.of(newExtras));
    }

    public List<Extra> getExtras() {
        return Collections.unmodifiableList(extras);
    }

    public void setBeverageFree() {
        isFree = true;
    }
    public boolean isBeverageFree() {
        return isFree;
    }
}
