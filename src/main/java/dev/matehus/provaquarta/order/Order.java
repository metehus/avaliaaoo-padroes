package dev.matehus.provaquarta.order;

import dev.matehus.provaquarta.ECommerce;
import dev.matehus.provaquarta.cart.Cart;

import java.util.Random;

public class Order {
    public static int numberIndex = 1;

    public int number;
    private OrderStatus status;
    public Cart cart;

    public Order(Cart cart) {
        this.number = numberIndex++;
        this.cart = cart;
        setStatus(OrderStatus.Confirmed);
    }

    public void setStatus(OrderStatus status) {
        OrderStatus oldStatus = this.status;
        this.status = status;
        ECommerce.getInstance().notifyOrderStatusUpdate(this, oldStatus);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.setStatus(OrderStatus.Canceled);
    }
}
