package com.sarcasm.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sarcasm.app.R;

public final class LoginActivity extends AppCompatActivity implements OnClickListener {
    private TextView password;
    private Button login;

    @Override
    protected final void onCreate(final @Nullable Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_login);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.password = findViewById(R.id.etPassword);
        this.login = findViewById(R.id.btnLogin);
        this.login.setOnClickListener(this);
    }

    @Override
    public final void onClick(final View v) {
        if (v != this.login || this.login == null || this.password == null) return;

        final String pass = this.password.getText().toString();
        if (pass.isEmpty()) return;

        System.out.printf("Pass: [%s]", pass);
    }
}

