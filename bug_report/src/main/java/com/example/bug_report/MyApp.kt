package com.example.bug_report

import android.R.attr.button
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import java.security.Provider


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get()
            .lifecycle
            .addObserver(ProcessLifecycleObserver())
        Log.i("APPPP:", baseContext.toString())
    }




    inner private class ProcessLifecycleObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onApplicationResumed() {
            val tag = "App: "
            Log.i(tag,"Resume")
            val intent = Intent(baseContext,OverlayShowingService::class.java)
            startService(intent)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onApplicationPaused() {
            val tag = "App: "
            Log.i(tag,"Pause")
            val intent = Intent(baseContext,OverlayShowingService::class.java)
            val value = OverlayShowingService()
            value.onDestroy()
        }



    }
}