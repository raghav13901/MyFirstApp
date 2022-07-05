package com.example.bug_report

import android.R
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast


class OverlayShowingService : Service(), OnTouchListener, View.OnClickListener {
    private var topLeftView: View? = null
    private var overlayedButton: Button? = null
    private var offsetX = 0f
    private var offsetY = 0f
    private var originalXPos = 0
    private var originalYPos = 0
    private var moving = true
    private var wm: WindowManager? = null
    private  val TAG = "OverlayShowingService"
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        overlayedButton = Button(this)
        overlayedButton!!.setBackgroundColor(Color.rgb(58, 176, 255))
        overlayedButton!!.setOnClickListener(this)
        overlayedButton!!.tag = "Overlay"
        val img: Drawable = overlayedButton!!.getContext().getResources().getDrawable(R.drawable.stat_notify_chat)
        overlayedButton!!.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null)
        val LAYOUT_FLAG: Int
        LAYOUT_FLAG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            LAYOUT_FLAG,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.LEFT or Gravity.TOP
        params.x = 0
        params.y = 0
        wm!!.addView(overlayedButton, params)
        topLeftView = View(this)
        val topLeftParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        )
        topLeftParams.gravity = Gravity.LEFT or Gravity.TOP
        topLeftParams.x = 0
        topLeftParams.y = 0
        topLeftParams.width = 0
        topLeftParams.height = 0
        wm!!.addView(topLeftView, topLeftParams)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Appppp:","Pausedd")
        if (overlayedButton != null) {
            wm!!.removeView(overlayedButton)
            wm!!.removeView(topLeftView)
            overlayedButton = null
            topLeftView = null
        }
//        overlayedButton!!.setVisibility(View.GONE);
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.rawX
            val y = event.rawY
            moving = false
            val location = IntArray(2)
            overlayedButton!!.getLocationOnScreen(location)
            originalXPos = location[0]
            originalYPos = location[1]
            offsetX = originalXPos - x
            offsetY = originalYPos - y
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val topLeftLocationOnScreen = IntArray(2)
            topLeftView!!.getLocationOnScreen(topLeftLocationOnScreen)
            println("topLeftY=" + topLeftLocationOnScreen[1])
            println("originalY=$originalYPos")
            val x = event.rawX
            val y = event.rawY
            val params = overlayedButton!!.layoutParams as WindowManager.LayoutParams
            val newX = (offsetX + x).toInt()
            val newY = (offsetY + y).toInt()
            if (Math.abs(newX - originalXPos) < 1 && Math.abs(newY - originalYPos) < 1 && !moving) {
                return false
            }
            params.x = newX - topLeftLocationOnScreen[0]
            params.y = newY - topLeftLocationOnScreen[1]
            wm!!.updateViewLayout(overlayedButton, params)
            moving = true
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (moving) {
                return true
            }
        }
        return false
    }


    override fun onClick(v: View) {
        Log.d(TAG, "onClick: ")
        val intent = Intent(this, MainActivity3::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
        Toast.makeText(this, "Overlay button click event", Toast.LENGTH_SHORT).show()
    }
}