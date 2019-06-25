package com.sarcasm.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;
import com.sarcasm.app.Receipt;

import java.io.Serializable;
import java.util.ArrayList;

public final class CategoryActivity extends AppCompatActivity {
    private LinearLayout list;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_category);
        this.list = findViewById(R.id.productList);

        final Serializable serialized = getIntent().getSerializableExtra("category");
        if(serialized == null || !(serialized instanceof Category)) {
            Toast.makeText(this, "Invalid serialized data", Toast.LENGTH_LONG).show();
            finishActivity(RESULT_CANCELED);

            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        /* Get the products from serialized */
        final Category category = (Category) serialized;
        final ArrayList<Category.Product> products = Category.Product.getProducts(category);

        /* Get screen width and height */
        final Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        /* Add all products */
        for(final Category.Product p : products)
            addProduct(p, point.x, point.y);
    }

    /** Adds a product to the interface */
    private final void addProduct(final Category.Product p, final int width, int value){
        final LinearLayout layout = new LinearLayout(this);
        final TextView price = new TextView(this);
        final TextView text = new TextView(this);
        value /= 6;

        /* Set layout specs */
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setMinimumWidth(width);
        layout.setMinimumHeight(value);

        /* Set price specs */
        final String amount = getPrice(p);

        price.setText(amount);
        price.setTextColor(Color.parseColor("#009246"));
        price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        price.setGravity(Gravity.CENTER_VERTICAL);
        price.setX(5);
        price.setWidth(value);
        price.setHeight(value);

        /* Set text specs */
        text.setTextColor(Color.parseColor("#009246"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        text.setText(p.getName());

        text.setGravity(Gravity.CENTER_VERTICAL);
        text.setX(value / 2);
        text.setWidth(value * 2);
        text.setHeight(value);

        /* Add everything to layouts */
        layout.addView(price);
        layout.addView(text);

        addButtons(layout, p, value);
        this.list.addView(layout);
    }

    /** Adds buttons to linear layout */
    private final void addButtons(final LinearLayout layout, final Category.Product p, int value){
        final LinearLayout actionBar = new LinearLayout(this);
        final ImageView plus = new ImageView(this);
        final ImageView minus = new ImageView(this);
        final TextView text = new TextView(this);
        final Receipt receipt = MainActivity.receipt;
        value /= 3;

        /* Set plus image specs */
        plus.setImageResource(R.drawable.ic_input_add);
        plus.setMinimumWidth(value);
        plus.setMinimumHeight(value);
        plus.setMaxWidth(value);
        plus.setMaxHeight(value);

        /* Set minus image specs */
        minus.setImageResource(R.drawable.presence_busy);
        minus.setMinimumWidth(value);
        minus.setMinimumHeight(value);
        minus.setMaxWidth(value);
        minus.setMaxHeight(value);

        /* Set amount specs */
        text.setText(Integer.toString(receipt.getAmount(p)));
        text.setInputType(InputType.TYPE_CLASS_NUMBER);

        /* Register listener */
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View v) {
                receipt.removeProduct(p);
                text.setText(Integer.toString(receipt.getAmount(p)));
                System.out.println("HALLO MINUS: " + MainActivity.receipt.getProducts());
            }
        });

        /* Register listener */
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(final View v) {
                MainActivity.receipt.addProduct(p);
                text.setText(Integer.toString(receipt.getAmount(p)));
                System.out.println("HALLO PLUS: " + MainActivity.receipt.getProducts());
            }
        });

        /* Add buttons and amount to action bar */
        actionBar.setGravity(Gravity.CENTER_VERTICAL);
        actionBar.setOrientation(LinearLayout.HORIZONTAL);
        actionBar.addView(minus);
        actionBar.addView(text);
        actionBar.addView(plus);

        /* Add the action bar to layout */
        layout.addView(actionBar);
    }

    /** Gets the price of a product as a string */
    public final String getPrice(final Category.Product p){
        final double normal = p.getPrice();
        final int rounded = (int)normal;
        final double decimal = normal - rounded;

        final StringBuilder string = new StringBuilder();
        string.append('€');
        string.append(rounded);

        if(decimal > 0) {
            string.append(',');
            string.append(Math.round(decimal * 100));
        }

        return string.toString();
    }
}
