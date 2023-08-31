package com.thushan.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thushan.ecommerce.MainActivity3;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.adapters.SliderAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    Button nextButton, backButton, sliderButton;
    TextView[] dots;
    Animation animation;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        nextButton = findViewById(R.id.next_btn);
        backButton = findViewById(R.id.back_btn);
        sliderButton = findViewById(R.id.get_started_btn);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        }

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(position);

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (position == 0) {
                            viewPager.setCurrentItem(position + 1, true);
                            sliderButton.setVisibility(View.INVISIBLE);
                        } else if (position == 1) {
                            viewPager.setCurrentItem(position + 1, true);
                            sliderButton.setVisibility(View.INVISIBLE);
                        } else {
                            viewPager.setCurrentItem(position + 1, true);
                            animation= AnimationUtils.loadAnimation(OnBoardingActivity.this,R.anim.slide_animation);
                            sliderButton.setAnimation(animation);
                            sliderButton.setVisibility(View.VISIBLE);
                        } /*else {
                        animation= AnimationUtils.loadAnimation(OnBoardingActivity.this,R.anim.slide_animation);
                        sliderButton.setAnimation(animation);
                        sliderButton.setVisibility(View.VISIBLE);
                    }*/

                    }

                });

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (position == 3) {
                            viewPager.setCurrentItem(position - 1, true);
                            sliderButton.setVisibility(View.INVISIBLE);
                        } else if (position == 2) {
                            viewPager.setCurrentItem(position - 1, true);
                            sliderButton.setVisibility(View.INVISIBLE);
                        } else {
                            viewPager.setCurrentItem(position - 1, true);
                            sliderButton.setVisibility(View.INVISIBLE);
                        }

                    }

                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        addDots(0);


    }


    private void addDots(int position) {

        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);

                sliderButton.setVisibility(View.INVISIBLE);

        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.purple_200));
        }

sliderButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
    }
});
    }

}