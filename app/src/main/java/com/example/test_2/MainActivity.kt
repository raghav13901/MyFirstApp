package com.example.test_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val svc = Intent(this, OverlayShowingService::class.java)
        startService(svc)

        val btn12 = findViewById<Button>(R.id.button12)
        btn12.setOnClickListener{
            val intent12 = Intent(this, MainActivity2::class.java)
            startActivity(intent12)
        }

    }
}

