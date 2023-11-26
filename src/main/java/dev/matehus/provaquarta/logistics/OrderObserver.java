package dev.matehus.provaquarta.logistics;

import dev.matehus.provaquarta.order.Order;
import dev.matehus.provaquarta.order.OrderStatus;

public interface OrderObserver {
    void onOrderStatusUpdate(Order order, OrderStatus oldStatus);
}
