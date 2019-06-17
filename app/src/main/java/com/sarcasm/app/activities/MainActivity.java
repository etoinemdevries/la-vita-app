package com.sarcasm.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import com.sarcasm.app.R;

public final class MainActivity extends AppCompatActivity implements OnClickListener{
    private ImageView category;
    private float x;
    private float y;
    private boolean moved;

    @Override
    protected final void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_main);

        this.category = findViewById(R.id.imgCat1);
        this.category.setOnClickListener(this);
    }

    @Override
    public final void onClick(final View v) {
        if (v != this.category) return;

        startActivity(new Intent(this, CategoryActivity.class));
    }

    @Override
    public final boolean onTouchEvent(final MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                /* If the motion is right-to-left */
                if (x < this.x + 5 && !this.moved) {
                    System.out.println("Switch");
                    startActivity(new Intent(this, CategoryActivity.class));
                    this.moved = true;
                }

            case MotionEvent.ACTION_DOWN:
                this.x = x;
                this.y = y;
                break;

            case MotionEvent.ACTION_UP:
                this.moved = false;
                break;
        }

        return false;
    }
}
