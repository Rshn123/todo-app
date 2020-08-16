package com.example.loginpage.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.loginpage.Main3Activity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        NotificationSchedular.showNotification(context, Main3Activity::class.java, intent!!.extras!!.getString("title"), "watch them now",intent.extras!!.getInt("id") )
    }
}