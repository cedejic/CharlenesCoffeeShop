package org.assignment.coffeeshop.order;

import org.assignment.coffeeshop.consumable.Consumable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private List<Consumable> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.NEW;
    private BigDecimal total;

    public Order() {}

    public Order(Consumable... newItems) {
        items.addAll(List.of(newItems));
    }

    public void addItem(Consumable newItem) {
        if (!OrderStatus.NEW.equals(status)) throw new IllegalStateException("Order must be in status NEW to add order item");
        items.add(newItem);
    }

    public void addItems(Consumable... newItems) {
        if (!OrderStatus.NEW.equals(status)) throw new IllegalStateException("Order must be in status NEW to add order items");
        items.addAll(List.of(newItems));
    }

    public List<Consumable> getItems() {
        return Collections.unmodifiableList(items);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        if (!OrderStatus.NEW.equals(status)) throw new IllegalStateException("Order must be in status NEW to set order total");
        this.total = total;
        status = OrderStatus.CHARGED;
    }
}
