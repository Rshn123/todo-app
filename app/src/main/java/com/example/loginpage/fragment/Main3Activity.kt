package com.example.loginpage.fragment

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.loginpage.AlarmReceiver
import com.example.loginpage.DataViewModel
import com.example.loginpage.R
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.backup.*
import kotlinx.android.synthetic.main.calander_and_time_popup.*
import java.util.*


class Main3Activity : AppCompatActivity() {

    var id = String()
    lateinit var dataViewModel: DataViewModel
    lateinit var dialog: Dialog
    lateinit var datePicker: DatePicker
    lateinit var timePicker: TimePicker
    val RQS_1 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        dialog = Dialog(this)
        dataViewModel = ViewModelProvider(
                this, DataViewModel.Factory(applicationContext)
        ).get(DataViewModel::class.java)

        val getIntent: Intent = intent
        val b: Bundle = getIntent.extras!!

        if (b != null) {
            id = b.get("id").toString()
            Log.d("0110", "skladjlkfsf" + id)
            val tableRow = dataViewModel.getTableRow(id)
            textView_title.text = tableRow.title
        }

        my_day.setOnClickListener {
            val trueOrFalse = dataViewModel.getTableRow(id).myDay
            selectedMyDay(!trueOrFalse)
            dataViewModel.updateMyDay(id, !trueOrFalse)
        }

        important_imageview_layout.setOnClickListener {
            val trueOrFalse = dataViewModel.getTableRow(id).isImportant
            selectedImportant(!trueOrFalse)
            dataViewModel.updateImportant(id, !trueOrFalse)

        }

        repeat.setOnClickListener {
            showRepeatPopUp()
        }

        reminder.setOnClickListener {
            showReminderPopUP()
        }

        due_date.setOnClickListener {
            showDueDatePopUp()
        }
    }

    override fun onResume() {
        super.onResume()
        val queryResult = dataViewModel.getTableRow(id)
        val trueOrFalse = queryResult.myDay
        val isImportant = queryResult.isImportant
        selectedMyDay(trueOrFalse)
        selectedImportant(isImportant)
    }

    private fun selectedMyDay(boolean: Boolean) {
        if (boolean) {
            my_day.background = ContextCompat.getDrawable(this, R.drawable.recycle_view_item_another)
        } else {
            my_day.background = ContextCompat.getDrawable(this, R.drawable.recycle_view_item)
        }
    }

    private fun selectedImportant(boolean: Boolean) {
        if (boolean) {
            is_important_imageView.background = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        } else {
            is_important_imageView.background = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun showRepeatPopUp() {
        dialog.setContentView(R.layout.backup)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.close_popup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showReminderPopUP() {
        dialog.setContentView(R.layout.calander_and_time_popup)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.calender.setBackgroundColor(Color.GRAY)
        dialog.cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.calender.setOnClickListener {
            dialog.calender_view.visibility = View.VISIBLE
            dialog.time_picker.visibility = View.GONE
            dialog.time.setBackgroundColor(Color.TRANSPARENT)
            dialog.calender.setBackgroundColor(Color.GRAY)
        }

        dialog.time.setOnClickListener {
            dialog.time_picker.visibility = View.VISIBLE
            dialog.calender_view.visibility = View.GONE
            dialog.time.setBackgroundColor(Color.GRAY)
            dialog.calender.setBackgroundColor(Color.TRANSPARENT)
        }
        dialog.show()
        datePicker = dialog.findViewById(R.id.calender_view)
        timePicker = dialog.findViewById(R.id.time_picker)


        dialog.save.setOnClickListener {
            val current = Calendar.getInstance()
            val cal = Calendar.getInstance()
            cal.set(datePicker.year, datePicker.month, datePicker.dayOfMonth, timePicker.currentHour, timePicker.currentMinute, 0)
//            val cal = Calendar.getInstance().apply {
//                timeInMillis = System.currentTimeMillis()
//                set(Calendar.HOUR_OF_DAY, 8)
//                set(Calendar.MINUTE, 30)
//            }

            if (cal < current) {
                Toast.makeText(this, "Invalid Date/Time", Toast.LENGTH_SHORT).show()
            } else {
                var alarmMgr: AlarmManager? = null
                lateinit var alarmIntent: PendingIntent
                alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmIntent = Intent(this, AlarmReceiver::class.java).let { intent ->
                    PendingIntent.getBroadcast(this, 0, intent, 0)
                }

                alarmMgr?.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        cal.timeInMillis,
                        1000 * 60 * 20,
                        alarmIntent
                )
            }
        }
    }

    private fun showDueDatePopUp() {
        dialog.setContentView(R.layout.due_date_layout)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.close_popup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setAlarm(targetCal: Calendar) {

    }
}
