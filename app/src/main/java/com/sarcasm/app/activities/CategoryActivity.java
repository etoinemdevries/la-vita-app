package com.sarcasm.app.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarcasm.app.R;

public class CategoryActivity extends AppCompatActivity {
    private LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.list = findViewById(R.id.productList);
    }

    private void addLayout() {
        final LinearLayout product = new LinearLayout(this);
        final TextView productInfo = new TextView(this);

        final LinearLayout actionBar = new LinearLayout(this);
        final ImageView minus = new ImageView(this);
        final ImageView plus = new ImageView(this);
        final TextView amount = new TextView(this);


        /* Productinfo LinearLayout specs */
        product.setOrientation(LinearLayout.HORIZONTAL);

        /* Product name + price string */
        productInfo.setText("Name" + "Price");
        productInfo.setTextColor(Color.parseColor("#009246"));
        productInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        /* Actionbar LinearLayout */
        actionBar.setGravity(Gravity.END);

        /* Minus image specs */
        minus.setImageResource(R.drawable.presence_busy);

        /* Plus image specs */
        plus.setImageResource(R.drawable.ic_input_add);

        /* Amount textview specs */
        amount.setInputType(InputType.TYPE_CLASS_NUMBER);


        /* Add stuff to eachother */

        actionBar.addView(minus);
        actionBar.addView(amount);
        actionBar.addView(plus);

        product.addView(productInfo);
        product.addView(actionBar);

        this.list.addView(product);
    }
}
