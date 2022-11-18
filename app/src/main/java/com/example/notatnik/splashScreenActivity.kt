package com.example.notatnikwsb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notatnik.MainActivity
import com.example.notatnik.R

class splashScreenActivity : AppCompatActivity() {


    private val splashTimeOut: Long = 1000 // 5 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, MainActivity::class.java))
            // close this activity
            finish()
        }, splashTimeOut)
    }
}