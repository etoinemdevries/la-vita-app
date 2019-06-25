package com.sarcasm.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;
import com.sarcasm.app.Receipt;

public final class MainActivity extends AppCompatActivity {
    public static Receipt receipt;
    private LinearLayout list;
    private ImageView logo;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.list = findViewById(R.id.categories);
        this.logo = findViewById(R.id.imgLogo);

        /* Onclicklistener for logo (temporary) */
        goToReceipt();

        /* Checks for table number */
        final int result = getIntent().getIntExtra("table", -1);
        if (result != -1) this.receipt = new Receipt(result);

        /* Get screen width and height */
        final Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        /* Add all categories */
        for (final Category c : Category.values())
            addCategory(c, point.x, point.y);
    }

    private final void addCategory(final Category c, final int width, final int height) {
        final LinearLayout layout = new LinearLayout(this);
        final ImageView img = new ImageView(this);
        final TextView text = new TextView(this);

        /* Set layout specs */
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setMinimumHeight(height / 6);
        layout.setMinimumWidth(width);

        /* Set image specs */
        img.setMinimumWidth(height / 6);
        img.setMinimumHeight(height / 6);
        img.setMaxWidth(height / 6);
        img.setMaxHeight(height / 6);
        img.setImageResource(c.getId());

        /* Set text specs */
        text.setTextColor(Color.parseColor("#009246"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        text.setText(c.getName());

        text.setGravity(Gravity.CENTER);
        text.setWidth(height / 6 * 5);
        text.setHeight(height / 6);

        /* Add click listener to category */
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public final void onClick(final View v) {
                if (Category.Product.getProducts(c).isEmpty()) {
                    Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_LONG).show();
                    return;
                }

                final Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("category", c);

                startActivityForResult(intent, RESULT_OK);
            }
        });

        /* Add everything to layouts */
        layout.addView(img);
        layout.addView(text);
        this.list.addView(layout);
    }

    private final void goToReceipt() {
        this.logo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent(MainActivity.this, ReceiptActivity.class);
                startActivityForResult(intent, RESULT_OK);
            }
        });
    }
}
