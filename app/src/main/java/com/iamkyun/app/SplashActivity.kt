package com.iamkyun.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, 2000)

    }

    companion object {
        private const val TAG = "SplashActivity"
    }
}