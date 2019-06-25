package com.sarcasm.app;

import java.util.ArrayList;

public final class Receipt {
    private final ArrayList<Category.Product> products = new ArrayList<>();
    private final int tableNumber;

    public Receipt(final int table) {
        this.tableNumber = table;
    }

    public final void addProduct(final Category.Product product) {
        this.products.add(product);
    }

    public final boolean removeProduct(final Category.Product product) {
        return !this.products.isEmpty() && this.products.remove(product);
    }

    public final double getTotalPrice() {
        double price = 0;

        for (final Category.Product p : this.products)
            price += p.getPrice();

        return price;
    }

    public final int getAmount(final Category.Product p) {
        int count = 0;

        for (final Category.Product product : this.products)
            if (p.getName() == product.getName()) count++;

        return count;
    }

    public final int getTableNumber() {
        return this.tableNumber;
    }

    public final ArrayList<Category.Product> getProducts() {
        return this.products;
    }

}
