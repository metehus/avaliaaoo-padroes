package dev.matehus.provaquarta.payment;

public class MercadoPagoProvider implements PaymentProvider {
    @Override
    public String getName() {
        return "Mercado Pago";
    }

    @Override
    public boolean pay(float amount) {
        System.out.println("[MP] Pagamento autorizado!");

        return true;
    }
}
