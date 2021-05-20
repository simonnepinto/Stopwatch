package com.simonne.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    ImageView anchor;
    Animation anim;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.startButton);
        b2 = (Button)findViewById(R.id.resetButton);
        b3 = (Button)findViewById(R.id.stopButton);

        anchor = (ImageView)findViewById(R.id.anchor);
        chronometer = (Chronometer)findViewById(R.id.chrono);

        b1.animate().translationY(120);
        b2.setAlpha(0);
        b3.setAlpha(0);

        anim = AnimationUtils.loadAnimation(this, R.anim.anim);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchor.startAnimation(anim);

                b3.animate().alpha(1).translationY(-80).setDuration(300).start();
                b2.animate().alpha(1).translationY(-80).setDuration(300).start();
                b1.animate().alpha(0).setDuration(300).start();

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchor.clearAnimation();
                chronometer.setBase(SystemClock.elapsedRealtime());
                anchor.startAnimation(anim);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.animate().alpha(1).translationY(120).setDuration(300).start();
                b2.animate().alpha(0).setDuration(300).start();
                b3.animate().alpha(0).setDuration(300).start();
                anchor.clearAnimation();
                chronometer.stop();
            }
        });

    }
}