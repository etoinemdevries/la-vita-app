package com.sarcasm.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarcasm.app.R;

public class CategoryActivity extends AppCompatActivity {

    private LinearLayout list;

    public String frisdranken[] = {
            "Cola", "Ice-tea", "7-up", "Cassis", "Sinas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.list = findViewById(R.id.productList);
        addLayout(frisdranken);
    }

    private void addLayout(String[] products) {
        for (int i = 0; i < products.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(products[i]);
            this.list.addView(tv);
        }
    }
}
