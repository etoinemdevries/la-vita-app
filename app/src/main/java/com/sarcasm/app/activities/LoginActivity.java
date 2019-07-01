package com.sarcasm.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sarcasm.app.R;
import com.sarcasm.app.utils.Connection;

public final class LoginActivity extends AppCompatActivity implements OnClickListener {
    private TextView password;
    private ImageView logo;
    private Button login;

    public LoginActivity(){
        new Thread(new Runnable() {
            @Override
            public final void run() {
                Connection connection = new Connection("172.20.10.7", 1337);
                connection.write("Hallo");
                connection.disconnect();
            }
        }).start();
    }

    @Override
    protected final void onCreate(final @Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);

        this.password = findViewById(R.id.etPassword);
        this.login = findViewById(R.id.btnLogin);
        this.login.setOnClickListener(this);

        this.logo = findViewById(R.id.imgLogo);
        this.logo.setImageResource(R.drawable.vita_logo);
    }

    @Override
    public final void onClick(final View v) {
        if (v != this.login || this.login == null || this.password == null) return;
        final String pass = this.password.getText().toString();

        if (pass.isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_LONG).show();
            return;
        }

        // TODO: Add authentication
        startActivity(new Intent(this, TableActivity.class));
    }
}

