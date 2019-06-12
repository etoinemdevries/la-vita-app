package com.sarcasm.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sarcasm.app.R;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
