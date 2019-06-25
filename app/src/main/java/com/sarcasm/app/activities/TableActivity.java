package com.sarcasm.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sarcasm.app.Category;
import com.sarcasm.app.R;
import com.sarcasm.app.Receipt;

public class TableActivity extends AppCompatActivity {
    private LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        this.list = findViewById(R.id.tableList);
        createLayout();
    }

    private void createLayout() {
        for (int i = 1; i < 15; i++ ) {
            final LinearLayout layout = new LinearLayout(this);
            final TextView text = new TextView(this);
            final ImageView img = new ImageView(this);
            final int j = i;

            /* Set layout specs */
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);

            /* Set text specs */
            text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            text.setGravity(Gravity.CENTER_VERTICAL);
            text.setPadding(30, 0, 0, 0);
            text.setTextSize(25);
            text.setText("Tafel " + j);

            /* Set image specs */
            img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            img.setPadding(0, 12, 30, 12);
            img.setImageResource(R.drawable.table_unoccupied);

            /* Set onclicklistener on layout */
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(final View v) {
                    final Receipt receipt = new Receipt(j);
                    final Intent intent = new Intent(TableActivity.this, MainActivity.class);

                    startActivityForResult(intent, RESULT_OK);
                }
            });

            /* Add everything to layout */
            layout.addView(text);
            layout.addView(img);
            this.list.addView(layout);
        }
    }

}
