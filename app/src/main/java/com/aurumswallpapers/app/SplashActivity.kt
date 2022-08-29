package com.aurumswallpapers.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Thread {
            Thread.sleep(2000)
            runOnUiThread {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
        }.start()
    }
}