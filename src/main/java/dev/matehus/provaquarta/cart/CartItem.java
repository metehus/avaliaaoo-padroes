package dev.matehus.provaquarta.cart;

import dev.matehus.provaquarta.entity.Product;

public class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    float getTotal() {
        return this.product.price * this.quantity;
    }
}
