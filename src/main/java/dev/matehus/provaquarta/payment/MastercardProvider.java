package dev.matehus.provaquarta.payment;

public class MastercardProvider implements PaymentProvider {
    @Override
    public String getName() {
        return "Mastercard";
    }

    @Override
    public boolean pay(float amount) {
        System.out.println("[MC] Pagamento autorizado!");

        return true;
    }
}
