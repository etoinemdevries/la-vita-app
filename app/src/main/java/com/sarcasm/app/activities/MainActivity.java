package com.sarcasm.app.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;

import java.io.Serializable;

public final class MainActivity extends AppCompatActivity {
    private ImageView category;
    private LinearLayout list;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        this.list = findViewById(R.id.categories);
        this.category = findViewById(R.id.imgLogo);

        final Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        /* Add all categories */
        for(final Category c : Category.values()) {
            final LinearLayout layout = new LinearLayout(this);
            final ImageView img = new ImageView(this);
            final TextView text = new TextView(this);

            /* Set layout specs */
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setMinimumHeight(point.y / 6);
            layout.setMinimumWidth(point.x);

            /* Set image specs */
            img.setMinimumWidth(point.y / 6);
            img.setMinimumHeight(point.y / 6);
            img.setMaxWidth(point.y / 6);
            img.setMaxHeight(point.y / 6);
            img.setImageResource(R.drawable.ic_launcher_round);

            /* Set text specs */
            text.setTextColor(Color.parseColor("#009246"));
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            text.setText(c.getName());

            text.setGravity(Gravity.CENTER);
            text.setWidth(point.y / 6 * 5);
            text.setHeight(point.y / 6);

            /* Add click listener to category */
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public final void onClick(final View v) {
                    final Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    intent.putExtra("category", c);
                    startActivity(intent);
                }
            });

            /* Add everything to layouts */
            layout.addView(img);
            layout.addView(text);
            this.list.addView(layout);
        }
    }
}
