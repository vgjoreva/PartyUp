package com.vergjor.android.partyup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.iv);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        imageView.startAnimation(anim);
        final Intent i = new Intent(this, ClientActivity.class);
        Thread timer = new Thread(){
          public void run(){
              try{
                  sleep(4000);
              }
              catch (InterruptedException e){
                  e.printStackTrace();
              }
              finally {
                  startActivity(i);
                  finish();
              }
          }
        };

        timer.start();
    }


}
