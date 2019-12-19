package com.tubes.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAct extends AppCompatActivity {

    Animation app_splash;
    Animation app_slogan_splash;
    ImageView app_logo;
    TextView app_slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Load Animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        app_slogan_splash = AnimationUtils.loadAnimation(this, R.anim.app_slogan_anim);

        // Load Element
        app_logo = findViewById(R.id.app_logo);
        app_slogan = findViewById(R.id.app_slogan);

        // Run an Animation
        app_logo.startAnimation(app_splash);
        app_slogan.startAnimation(app_slogan_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goLoginAct = new Intent(SplashAct.this, LoginForm.class);
                startActivity(goLoginAct);
                finish();
            }
        }, 2500);
    }
}
