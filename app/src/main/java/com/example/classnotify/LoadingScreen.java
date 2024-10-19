package com.example.classnotify;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        ImageView splashIcon = findViewById(R.id.splashIcon);

        // Load animation
        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_loading_screen);
        splashIcon.startAnimation(splashAnimation);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this, Choices.class));
                finish();
            }
        }, 3000);
    }
}