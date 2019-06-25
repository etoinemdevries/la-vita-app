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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
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

    private final void addProduct(final Category.Product p, final int width, int value){
        final LinearLayout layout = new LinearLayout(this);
        final ImageView img = new ImageView(this);
        final TextView text = new TextView(this);
        value /= 6;

        /* Set layout specs */
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setMinimumWidth(width);
        layout.setMinimumHeight(value);

        /* Set image specs */
        img.setMinimumWidth(value);
        img.setMinimumHeight(value);
        img.setMaxWidth(value);
        img.setMaxHeight(value);
        img.setImageResource(R.drawable.ic_launcher_round);

        /* Set text specs */
        text.setTextColor(Color.parseColor("#009246"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        text.setText(p.getName());

        text.setGravity(Gravity.CENTER_VERTICAL);
        text.setX(value / 2);
        text.setWidth(value * 2);
        text.setHeight(value);

        /* Add everything to layouts */
        layout.addView(img);
        layout.addView(text);
        //addButtons(layout, value, 0);

        this.list.addView(layout);
    }

    /** Adds buttons to linear layout */
    private final void addButtons(final LinearLayout layout, int value, final int amount){
        final LinearLayout actionBar = new LinearLayout(this);
        final ImageView plus = new ImageView(this);
        final ImageView minus = new ImageView(this);
        final TextView text = new TextView(this);
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
        text.setText(Integer.toString(amount));
        text.setInputType(InputType.TYPE_CLASS_NUMBER);

        /* Add buttons and amount to action bar */
        actionBar.setGravity(Gravity.CENTER_VERTICAL);
        actionBar.addView(minus);
        actionBar.addView(text);
        actionBar.addView(plus);

        /* Add the action bar to layout */
        layout.addView(actionBar);
    }

    private void addLayout() {
        final LinearLayout product = new LinearLayout(this);
        final TextView productInfo = new TextView(this);

        final LinearLayout actionBar = new LinearLayout(this);
        final ImageView minus = new ImageView(this);
        final ImageView plus = new ImageView(this);
        final TextView amount = new TextView(this);

        /* Product info LinearLayout specs */
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
