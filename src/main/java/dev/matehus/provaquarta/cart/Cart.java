package dev.matehus.provaquarta.cart;

import dev.matehus.provaquarta.entity.Product;
import dev.matehus.provaquarta.order.Order;
import dev.matehus.provaquarta.payment.PaymentProvider;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private PaymentProvider paymentProvider;
    public String shippingAddress = "Não definido";
    public List<CartItem> items = new ArrayList<>();

    public Cart(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public void addProduct(Product product, int quantity) {
        this.items.add(
            new CartItem(product, quantity)
        );
    }

    public float getTotal() {
        float total = 0f;

        for (CartItem item: this.items)
            total += item.getTotal();

        return total;
    }

    public Order checkout() {
        System.out.println("\nFinalização do carrinho de compras");
        for (int i = 0; i < this.items.size(); i++) {
            CartItem item = this.items.get(i);
            Product product = item.product;
            System.out.printf("%d. %s (%dx) - R$ %.2f\n", i + 1, product.name, item.quantity, item.getTotal());
        }

        System.out.printf("Total: R$ %.2f\n", this.getTotal());
        System.out.println("Pagamento realizado por: " + paymentProvider.getName());

        paymentProvider.pay(this.getTotal());


        return new Order(this);
    }
}
