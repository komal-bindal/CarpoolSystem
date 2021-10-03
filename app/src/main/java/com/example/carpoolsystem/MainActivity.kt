package com.example.carpoolsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent  = Intent(this, SignupScreen1::class.java)

        startActivity(intent)
        this.finish()

    }
}