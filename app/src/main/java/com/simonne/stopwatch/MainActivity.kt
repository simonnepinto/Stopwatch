package com.simonne.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var b1: Button; lateinit var b2: Button;lateinit var b3: Button
    lateinit var anchor: ImageView
    lateinit var anim: Animation
    lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById<Button>(R.id.startButton)
        b2 = findViewById<Button>(R.id.resetButton)
        b3 = findViewById<Button>(R.id.stopButton)
        anchor = findViewById<ImageView>(R.id.anchor)
        chronometer = findViewById<Chronometer>(R.id.chrono)

        b1.animate().translationY(120f)
        b2.alpha = 0f
        b3.alpha = 0f

        anim = AnimationUtils.loadAnimation(this, R.anim.anim)

        b1.setOnClickListener {
            anchor.startAnimation(anim)
            b3.animate().alpha(1f).translationY(-80f).setDuration(300).start()
            b2.animate().alpha(1f).translationY(-80f).setDuration(300).start()
            b1.animate().alpha(0f).setDuration(300).start()
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
        }

        b2.setOnClickListener {
            anchor.clearAnimation()
            chronometer.base = SystemClock.elapsedRealtime()
            anchor.startAnimation(anim)
        }

        b3.setOnClickListener {
            b1.animate().alpha(1f).translationY(120f).setDuration(300).start()
            b2.animate().alpha(0f).setDuration(300).start()
            b3.animate().alpha(0f).setDuration(300).start()
            anchor.clearAnimation()
            chronometer.stop()
        }
    }
}