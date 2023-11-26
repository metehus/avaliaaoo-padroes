package dev.matehus.provaquarta;

import dev.matehus.provaquarta.cart.Cart;
import dev.matehus.provaquarta.order.Order;
import dev.matehus.provaquarta.order.OrderStatus;
import dev.matehus.provaquarta.payment.MastercardProvider;
import dev.matehus.provaquarta.payment.MercadoPagoProvider;

public class Main {
    public static void main(String[] args) throws Exception {
        ECommerce eCommerce = ECommerce.getInstance();
        eCommerce.setDefaultPaymentProvider(new MercadoPagoProvider());

        // cria carrinho (vai usar mercado pago por padrao)
        Cart cart1 = eCommerce.createCart();

        cart1.addProduct(ProdutosDemo.teclado, 1);
        cart1.addProduct(ProdutosDemo.pendrive, 3);
        cart1.addProduct(ProdutosDemo.mouse, 1);
        cart1.shippingAddress = "Rua x número 20";

        // finaliza carrinho
        Order pedido1 = cart1.checkout();

        // define o provedor de pagamento padrao do ecommerce
        eCommerce.setDefaultPaymentProvider(new MastercardProvider());

        // cria um novo carrinho, dessa vez vai usar mastercard
        Cart cart2 = eCommerce.createCart();
        cart2.addProduct(ProdutosDemo.webcam, 2);
        cart2.shippingAddress = "Rua dos bobos número zero";

        // vai pagar usando mastercard
        Order pedido2 = cart2.checkout();

        // marca pedido como entregue
        pedido1.setStatus(OrderStatus.Delivered);
        // cancela pedido depois de entregue
        pedido1.cancel();

        // cancela pedido 2 antes da entrega
        pedido2.cancel();
    }

}