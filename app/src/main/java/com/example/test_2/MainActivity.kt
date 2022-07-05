package com.example.test_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bug_report.OverlayShowingService as OSS

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        if (!Settings.canDrawOverlays(this)) {
            val intent =
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 0)
        }
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

}

