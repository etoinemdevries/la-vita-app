package com.sarcasm.app;

import java.util.ArrayList;

public class Receipt {
    private Integer tableNumber;
    private ArrayList ids;

    public Receipt(int table) {
        this.tableNumber = table;
        this.ids = new ArrayList<>();
    }

    public void addId(int id) {
        this.ids.add(id);
    }
}
