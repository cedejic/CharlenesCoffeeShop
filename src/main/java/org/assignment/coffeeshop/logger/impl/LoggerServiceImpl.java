package org.assignment.coffeeshop.logger.impl;

import org.assignment.coffeeshop.consumable.Consumable;
import org.assignment.coffeeshop.consumable.beverage.Beverage;
import org.assignment.coffeeshop.consumable.extra.Extra;
import org.assignment.coffeeshop.order.Order;
import org.assignment.coffeeshop.logger.LoggerService;

import java.math.BigDecimal;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assignment.coffeeshop.util.FormattingUtils.formatCurrency;
import static org.assignment.coffeeshop.util.FormattingUtils.getDoubleValue;

public class LoggerServiceImpl implements LoggerService {

    private static final int CHARACTER_COUNT = 50;
    private static Logger logger;

    static {
        Logger mainLogger = Logger.getLogger("org.assignment");
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            private static final String FORMAT = "%1$s %n";

            @Override
            public String format(LogRecord logRecord) {
                return String.format(FORMAT, logRecord.getMessage());
            }
        });
        mainLogger.addHandler(consoleHandler);
        logger = Logger.getLogger(LoggerServiceImpl.class.getName());
    }

    @Override
    public void print(Order order) {
        printHeader();
        printSeparatorLine();

        int itemCounter = 0;
        for (Consumable consumable: order.getItems()) {
            printItem(++itemCounter, consumable);

            int extraCounter = 0;
            if (consumable instanceof Beverage) {
                for (Extra extra:  ((Beverage) consumable).getExtras()) {
                    printExtra(++extraCounter, extra);
                }
            }
        }

        printSeparatorLine();
        printFooter(order);
    }

    private void printHeader() {
        logger.info("Charlene's Coffee Corner");
    }

    private void printSeparatorLine() {
        String line = Stream.generate(() -> String.valueOf('='))
            .limit(CHARACTER_COUNT)
            .collect(Collectors.joining());
        logger.info(line);
    }

    private void printItem(int rownum, Consumable consumable) {
        String format = BigDecimal.ZERO.equals(consumable.getCost()) ?
            "%2d. %-" + (CHARACTER_COUNT - 20) + "s FREE %10s" :
            "%2d. %-" + (CHARACTER_COUNT - 15) + "s %10s";

        String itemLine = String.format(format,
            rownum, consumable.getDescription(), formatCurrency(getDoubleValue(consumable.getCost())));
        logger.info(itemLine);
    }

    private void printExtra(int rownum, Extra extra) {
        String format = BigDecimal.ZERO.equals(extra.getCost()) ?
            "%7d) %-" + (CHARACTER_COUNT - 25) + "s FREE %10s" :
            "%7d) %-" + (CHARACTER_COUNT - 20) + "s %10s";

        String extraLine = String.format(format,
            rownum, extra.getDescription(), formatCurrency(getDoubleValue(extra.getCost())));
        logger.info(extraLine);
    }

    private void printFooter(Order order) {
        String format = "TOTAL: %" + (CHARACTER_COUNT - 7) + "s%n%n";

        String footer = String.format(format,
            formatCurrency(getDoubleValue(order.getTotal())));
        logger.info(footer);
    }
}
