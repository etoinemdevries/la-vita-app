package com.sarcasm.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sarcasm.app.R;

public class ShowActivity extends AppCompatActivity {
    private final LinearLayout list = findViewById(R.id.buttonList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        addButton("La Vita E Bella", 1);
        addButton("Angry Geese", 2);
        addButton("Progressive Web App", 3);
    }

    private void addButton(final String text, final int go) {
        Button btn =  new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 300);
        lp.setMargins(30,30,30,30);
        btn.setLayoutParams(lp);
        btn.setText(text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent;
                switch (go) {
                    case 1:
                        intent = new Intent(ShowActivity.this, LoginActivity.class);
                        break;
                    case 2:
                        intent = new Intent(ShowActivity.this, MainActivity.class);
                        break;
                    case 3:
                        intent = new Intent(ShowActivity.this, PwaActivity.class);
                        break;
                }

                startActivityForResult(intent, RESULT_OK);
            }
        });
        this.list.addView(btn);
    }
}
