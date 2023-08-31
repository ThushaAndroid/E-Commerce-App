package com.thushan.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.thushan.ecommerce.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int LIMIT=5000;
    TextView title;
    ImageView img;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        title=findViewById(R.id.textView);
        img=findViewById(R.id.imageView);

        animation= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.fall_down);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                title.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);

                title.setAnimation(animation);
                img.setAnimation(animation);
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },LIMIT);
    }
}