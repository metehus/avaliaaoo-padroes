package dev.matehus.provaquarta;

import dev.matehus.provaquarta.cart.Cart;
import dev.matehus.provaquarta.logistics.LogisticAgent;
import dev.matehus.provaquarta.logistics.OrderObserver;
import dev.matehus.provaquarta.order.Order;
import dev.matehus.provaquarta.order.OrderStatus;
import dev.matehus.provaquarta.payment.PaymentProvider;

import java.util.ArrayList;
import java.util.List;

public class ECommerce {
    public static ECommerce instance = new ECommerce();
    private PaymentProvider defaultPaymentProvider;
    public List<LogisticAgent> logisticAgents = new ArrayList<>();

    ECommerce() {
        // cria agentes
        this.logisticAgents.add(new LogisticAgent("Delta Entregas", "Curitiba"));
    }

    public static ECommerce getInstance() {
        return instance;
    }

    Cart createCart() throws Exception {
        if (defaultPaymentProvider == null) {
            throw new Exception("O provedor de pagamento padrão não está definido!");
        }
        return new Cart(defaultPaymentProvider);
    }

    void setDefaultPaymentProvider(PaymentProvider paymentProvider) {
        this.defaultPaymentProvider = paymentProvider;
    }

    public void notifyOrderStatusUpdate(Order order, OrderStatus oldStatus) {
        for (OrderObserver observer: logisticAgents) {
            observer.onOrderStatusUpdate(order, oldStatus);
        }
    }
}
