package com.example.bug_report

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val back_btn = findViewById<Button>(R.id.back_button)
        back_btn.setOnClickListener{
            finish()
        }

        val record_btn = findViewById<Button>(R.id.record_button)
        record_btn.setOnClickListener{
            Log.i("TAG", "SERIAL: " + Build.SERIAL)
            Log.i("TAG","MODEL: " + Build.MODEL)
            Log.i("TAG","ID: " + Build.ID)
            Log.i("TAG","Manufacture: " + Build.MANUFACTURER)
            Log.i("TAG","brand: " + Build.BRAND)
            Log.i("TAG","type: " + Build.TYPE)
            Log.i("TAG","user: " + Build.USER)
            Log.i("TAG","BASE: " + Build.VERSION_CODES.BASE)
            Log.i("TAG", "TIME " + Build.TIME)
            Log.i("TAG","SDK  " + Build.VERSION.SDK)
            Log.i("TAG","BOARD: " + Build.BOARD)
            Log.i("TAG","BRAND " + Build.BRAND)
            Log.i("TAG","HOST " + Build.HOST)
            Log.i("TAG","FINGERPRINT: "+ Build.FINGERPRINT)
            Log.i("TAG","Version Code: " + Build.VERSION.RELEASE)
            Log.i("TAG","INCREMENTAL " + Build.VERSION.INCREMENTAL)
        }
    }
}