package com.example.test_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bug_report.OverlayShowingService as OSS

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val svc = Intent(this, OSS::class.java)
        startService(svc)
         val TAG = "MainActivity"
        val btn12 = findViewById<Button>(R.id.button12)
        btn12.setOnClickListener{
            val intent12 = Intent(this, MainActivity2::class.java)
            startActivity(intent12)
        }
    }

    public override fun onPause() {
        val TAG = "Main Activity";
        super.onPause()
        val svc = Intent(this, OSS::class.java)
        stopService(svc)
        Log.i(TAG,"Service Stopped.")
    }

    public override fun onResume() {
        super.onResume()
        val TAG = "Main Activity";
        super.onPause()
        val svc = Intent(this, OSS::class.java)
        startService(svc)
        Log.i(TAG,"Service Restarted.")
    }
}

