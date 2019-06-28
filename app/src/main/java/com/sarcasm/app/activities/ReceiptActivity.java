package com.sarcasm.app.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;
import com.sarcasm.app.Receipt;

public class ReceiptActivity extends AppCompatActivity {
    private LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        this.list = findViewById(R.id.productList);

        /* Get screen width and height */
        final Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        /* Receipt */
        final Receipt receipt = MainActivity.receipt;

        /* Add all products */
        for(final Category.Product p : Category.Product.values()){
            final int count = receipt.getAmount(p);
            if (count <= 0) continue;

            final String text = p.getName() + (count > 1 ? " (x" + count + ")" : "");
            addLayout(text, getPrice((p.getPrice() * count)), point.x, point.y);
        }

        /* Add total price section */
        if(!receipt.getProducts().isEmpty()) {
            addLayout("Totaal", getPrice(receipt.getTotalPrice()), point.x, point.y).setTextColor(Color.BLACK);
        }

        /* Add onclicklisteners for toggling of notition */
        openNotition();
        closeNotition();
    }

    /** Adds a product to the interface */
    private final TextView addLayout(final String textString, final String priceString, final int width, int value){
        value /= 6;
        final LinearLayout layout = makeLayout(width, value);
        final TextView price = makePrice(priceString, value);
        final TextView text = makeText(textString, value);

        /* Add everything to layouts */
        layout.addView(text);
        layout.addView(price);

        this.list.addView(layout);
        return text;
    }

    private final LinearLayout makeLayout(final int width, int value) {
        final LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setMinimumWidth(width);
        layout.setMinimumHeight(value);

        return layout;
    }

    private final TextView makeText(final String text, int value) {
        final TextView view = new TextView(this);

        view.setTextColor(Color.parseColor("#009246"));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        view.setText(text);

        view.setGravity(Gravity.CENTER_VERTICAL);
        view.setX(value / 2);
        view.setWidth(value * 2);
        view.setHeight(value);
        return view;
    }

    private final TextView makePrice(final String money, int value) {
        final TextView price = new TextView(this);
        price.setText(money);
        price.setTextColor(Color.parseColor("#009246"));
        price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        price.setGravity(Gravity.CENTER_VERTICAL);
        price.setX(5);
        price.setWidth(value);
        price.setHeight(value);

        return price;
    }

    /** Gets the price of a product as a string */
    private final String getPrice(final double price){
        final int rounded = (int)price;
        final double decimal = price - rounded;

        final StringBuilder string = new StringBuilder();
        string.append('€');
        string.append(rounded);

        if(decimal > 0) {
            string.append(',');
            string.append(Math.round(decimal * 100));
            return string.toString();
        }

        string.append(",-");
        return string.toString();
    }

    private final void openNotition() {
        final Button toggleButton = findViewById(R.id.btnNotition);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View v) {
                toggleButton.setVisibility(Button.GONE);
                findViewById(R.id.notition).setVisibility(LinearLayout.VISIBLE);
            }
        });
    }
    private final void closeNotition() {
        findViewById(R.id.btnCloseNotition).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View v) {
                findViewById(R.id.notition).setVisibility(LinearLayout.GONE);
                findViewById(R.id.btnNotition).setVisibility(Button.VISIBLE);
            }
        });
    }
}
