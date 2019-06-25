package com.sarcasm.app.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;
import com.sarcasm.app.Receipt;

import java.util.ArrayList;

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
        /* Sorting of products TODO */
        //final ArrayList<Category.Product> values = Category.Product.values();

        for(final Category.Product p : Category.Product.values()){
            final int count = receipt.getAmount(p);
            if (count <= 0) continue;

            addProduct(p, count, point.x, point.y);
        }
    }

    /** Adds a product to the interface */
    private final void addProduct(final Category.Product p, final int count, final int width, int value){
        final LinearLayout layout = new LinearLayout(this);
        final TextView price = new TextView(this);
        final TextView text = new TextView(this);
        value /= 6;

        /* Set layout specs */
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setMinimumWidth(width);
        layout.setMinimumHeight(value);

        /* Set price specs */
        final double money = p.getPrice();

        price.setText(getPrice(money * count));
        price.setTextColor(Color.parseColor("#009246"));
        price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        price.setGravity(Gravity.CENTER_VERTICAL);
        price.setX(5);
        price.setWidth(value);
        price.setHeight(value);

        /* Set text specs */
        text.setTextColor(Color.parseColor("#009246"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        if(count > 1) text.setText(p.getName() + " (x" + count + ")");
        else text.setText(p.getName());

        text.setGravity(Gravity.CENTER_VERTICAL);
        text.setX(value / 2);
        text.setWidth(value * 2);
        text.setHeight(value);

        /* Add everything to layouts */
        layout.addView(text);
        layout.addView(price);

        this.list.addView(layout);
    }

    /** Gets the price of a product as a string */
    public final String getPrice(final double price){
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
}
