package com.sarcasm.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarcasm.app.R;

public class TableActivity extends AppCompatActivity {
    private LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        this.list = findViewById(R.id.tableList);

        for (int i = 1; i < 15; i++ ) {
            final LinearLayout layout = new LinearLayout(this);
            final TextView text = new TextView(this);
            final ImageView img = new ImageView(this);

            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

            layout.setOrientation(LinearLayout.HORIZONTAL);

            text.setGravity(Gravity.CENTER_VERTICAL);
            text.setPadding(30, 0, 0, 0);
            text.setTextSize(25);
            text.setText("Tafel " + i);

            img.setPadding(0, 12, 30, 12);
            img.setImageResource(R.drawable.table_unoccupied);

            layout.addView(text);
            layout.addView(img);
            this.list.addView(layout);
        }
    }
}
