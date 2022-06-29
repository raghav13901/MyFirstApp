package com.example.test_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val btn31 = findViewById<Button>(R.id.button31)
        btn31.setOnClickListener{
            val intent12 = Intent(this, MainActivity::class.java)
            startActivity(intent12)
        }

        val btn32 = findViewById<Button>(R.id.button32)
        btn32.setOnClickListener{
            val intent12 = Intent(this, MainActivity2::class.java)
            startActivity(intent12)
        }
    }
}