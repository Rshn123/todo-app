package com.example.loginpage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.loginpage.fragment.Main3Activity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"cliekckd",Toast.LENGTH_SHORT).show()

    }

}