package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 private static int SPLASH_TIME_OUT=5000;

    Animation topAnimation;
    Animation bottomAnimation;
    Animation middleAnimation;
    View first,second,third,fourth,fifth,six;
    TextView logo,motto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animations);
        bottomAnimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animations);
        middleAnimation=AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        first=findViewById(R.id.first_line);
        second=findViewById(R.id.second_line);
        third=findViewById(R.id.third_line);
        fourth=findViewById(R.id.fourth_line);
        fifth=findViewById(R.id.fifth_line);
        six=findViewById(R.id.sixth_line);
        logo=findViewById(R.id.logo);
        motto=findViewById(R.id.slogan);

        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        six.setAnimation(topAnimation);

        logo.setAnimation(middleAnimation);
        motto.setAnimation(bottomAnimation);

        new Handler().postDelayed(() ->{
            Intent intent= new Intent(MainActivity.this,Login.class);

            startActivity(intent);

        },8000);
    }
}