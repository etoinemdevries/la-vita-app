package com.sarcasm.app;

import java.util.ArrayList;

public class Receipt {
    private final ArrayList<Category.Product> products = new ArrayList<>();
    private final int tableNumber;

    public Receipt(final int table) {
        this.tableNumber = table;
    }

    public void addProduct(final Category.Product product) {
        this.products.add(product);
    }

    public boolean removeProduct(final Category.Product product) {
        return !this.products.isEmpty() && this.products.remove(product);
    }

    public double getTotalPrice() {
        double price = 0;

        for (final Category.Product p : this.products)
            price += p.getPrice();

        return price;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public ArrayList<Category.Product> getProducts() {
        return this.products;
    }

}
