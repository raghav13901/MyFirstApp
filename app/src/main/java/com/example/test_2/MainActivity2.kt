package com.example.test_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.bug_report.OverlayShowingService

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val svc = Intent(this, OverlayShowingService::class.java)
        startService(svc)
        val btn21 = findViewById<Button>(R.id.button21)
        btn21.setOnClickListener{
            val intent12 = Intent(this, MainActivity::class.java)
            startActivity(intent12)
        }
    }


}