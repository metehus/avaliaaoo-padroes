package dev.matehus.provaquarta.payment;

public interface PaymentProvider {
    String getName();

    boolean pay(float amount);
}
