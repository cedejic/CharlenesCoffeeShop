package org.assignment.coffeeshop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class FormattingUtils {

    private FormattingUtils() {}

    public static String formatCurrency(double value) {
        NumberFormat numberFormat =  NumberFormat.getCurrencyInstance(new Locale("de", "CH"));
        return numberFormat.format(value);
    }

    public static double getDoubleValue(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
