package org.assignment.coffeeshop.loyalty;

public class LoyaltyCard {

    private int totalBeverageCount = 0;

    public void addBeverage() {
        ++totalBeverageCount;
    }

    public int getTotalBeverageCount() {
        return totalBeverageCount;
    }

    public boolean isEligibleForFreeBeverage() {
        return totalBeverageCount % 5 == 0;
    }
}
