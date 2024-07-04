package com.coderbinotechworld.cricketive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imageView = findViewById<ImageView>(R.id.iv_txt_logo)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 600
        imageView.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)

    }
}