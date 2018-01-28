package ru.job4j.interview.orderbook;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static ru.job4j.interview.orderbook.Operation.*;

/**
 * Order book.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class OrderBook {

    private final Book book = new Book();
    private String name;

    OrderBook(String name) {
        this.name = name;
    }

    /**
     * Add order to bid or ask.
     *
     * @param order order
     */
    public void add(final Order order) {
        Order updatedOrder = tradeIfPossible(order,
            book.getTradeBook(revertOperation(order.getOperation())));
        placeOrder(updatedOrder, book.getTradeBook(updatedOrder.getOperation()));
    }

    /**
     * Delete order from bid or ask collection.
     *
     * @param id id with which the specified order is to be associated
     * @return {@code true} if deleting is successfully
     */
    public boolean delete(final int id) {
        return removeOrder(id, book.getTradeBook(BUY))
            || removeOrder(id, book.getTradeBook(SELL));
    }

    private void placeOrder(final Order updatedOrder,
                            final Map<Double, TreeMap<Integer, Order>> collection) {
        if (updatedOrder.getVolume() > 0) {
            TreeMap<Integer, Order> orders = collection.getOrDefault(
                updatedOrder.getPrice(), new TreeMap<>());
            orders.putIfAbsent(updatedOrder.getOrderId(), updatedOrder);
            collection.put(updatedOrder.getPrice(), orders);
        }
    }

    private Order tradeIfPossible(final Order order,
                                  final Map<Double, TreeMap<Integer, Order>> collection) {
        Iterator<Map.Entry<Double, TreeMap<Integer, Order>>> priceIt =
            collection.entrySet().iterator();

        while (priceIt.hasNext() && order.getVolume() > 0) {
            Map.Entry<Double, TreeMap<Integer, Order>> entry = priceIt.next();
            Iterator<Map.Entry<Integer, Order>> orderIt =
                entry.getValue().entrySet().iterator();
            while (orderIt.hasNext()) {
                Map.Entry<Integer, Order> record = orderIt.next();
                if (!trade(order, record.getValue())) {
                    return order;
                }
                if (record.getValue().getVolume() == 0) {
                    orderIt.remove();
                }
            }
            if (entry.getValue().isEmpty()) {
                priceIt.remove();
            }
        }
        return order;
    }

    private boolean trade(final Order processedOrder, final Order proposedOrder) {
        if (processedOrder.getOperation().equals(SELL)) {
            return matchOffer(processedOrder, proposedOrder);
        } else {
            return matchOffer(proposedOrder, processedOrder);
        }
    }

    private boolean matchOffer(final Order incomeOrder, final Order existOrder) {
        if (existOrder.getPrice() >= incomeOrder.getPrice()) {
            swap(incomeOrder, existOrder);
            return true;
        }
        return false;
    }

    private void swap(final Order order1, final Order order2) {
        int amount = order2.getVolume() - order1.getVolume();
        if (amount > 0) {
            order2.setVolume(amount);
            order1.setVolume(0);
        } else {
            order1.setVolume(-amount);
            order2.setVolume(0);
        }
    }

    private boolean removeOrder(final int id,
                                final Map<Double, TreeMap<Integer, Order>> orders) {
        for (Map.Entry<Double, TreeMap<Integer, Order>> entry : orders.entrySet()) {
            if (entry.getValue().remove(id) != null && entry.getValue().isEmpty()) {
                orders.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    public TreeMap<Double, TreeMap<Integer, Order>> getBids() {
        return book.getBids();
    }

    public TreeMap<Double, TreeMap<Integer, Order>> getAsks() {
        return book.getAsks();
    }

    public String getName() {
        return name;
    }
}
