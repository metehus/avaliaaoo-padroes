package dev.matehus.provaquarta.logistics;

import dev.matehus.provaquarta.order.Order;
import dev.matehus.provaquarta.order.OrderStatus;

public class LogisticAgent implements OrderObserver {
    String nome;
    String location;

    public LogisticAgent(String nome, String location) {
        this.nome = nome;
        this.location = location;
    }

    @Override
    public void onOrderStatusUpdate(Order order, OrderStatus oldStatus) {
        if (order.getStatus() == OrderStatus.Confirmed) {
            System.out.printf("[%s] Pedido #%d recebido! Enviando pacote para: %s\n", this.nome, order.number, order.cart.shippingAddress);
            order.setStatus(OrderStatus.Shipped);
        } else if (order.getStatus() == OrderStatus.Canceled) {
            // cancelado depois da entrega
            if (oldStatus == OrderStatus.Delivered) {
                System.out.printf("[%s] Pedido #%d cancelado após entrega. Iniciando protocolo de devolução\n", this.nome, order.number);
            } else if (oldStatus == OrderStatus.Shipped) {
                System.out.printf("[%s] Pedido #%d cancelado antes da entrega. Cancelando envio\n", this.nome, order.number);
            }
        } else if (order.getStatus() == OrderStatus.Delivered) {
            System.out.printf("[%s] Pedido #%d entregue ao cliente\n", this.nome, order.number);
        }
    }
}
