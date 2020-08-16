package com.example.loginpage

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.loginpage.fragment.AlarmReceiver
import com.example.loginpage.fragment.NotificationSchedular
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.backup.*
import kotlinx.android.synthetic.main.calander_and_time_popup.*


class Main3Activity : AppCompatActivity() {

    var id = String()
    var number: Int = 0
    lateinit var dataViewModel: DataViewModel
    lateinit var dialog: Dialog
    lateinit var datePicker: DatePicker
    lateinit var timePicker: TimePicker
    lateinit var sharedPreferences: SharedPreferences
    lateinit var b: Bundle

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        sharedPreferences = getSharedPreferences("todoConfiguration", 0)

        dialog = Dialog(this)
        dataViewModel = ViewModelProvider(
            this, DataViewModel.Factory(applicationContext)
        ).get(DataViewModel::class.java)

        val getIntent: Intent = intent
        b = getIntent.extras!!

        if (b != null) {
            id = b.get("id").toString()
            number = b.getInt("number")
            Log.d("566",""+number)
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

        showDateAndTime()
    }

    override fun onResume() {
        super.onResume()
        val queryResult = dataViewModel.getTableRow(id)
        val trueOrFalse = queryResult.myDay
        val isImportant = queryResult.isImportant
        val reminder = queryResult.reminder
        selectedMyDay(trueOrFalse)
        selectedImportant(isImportant)
        selectReminder(reminder)
    }

    private fun selectedMyDay(boolean: Boolean) {
        if (boolean) {
            my_day.background =
                ContextCompat.getDrawable(this, R.drawable.recycle_view_item_another)
        } else {
            my_day.background = ContextCompat.getDrawable(this, R.drawable.recycle_view_item)
        }
    }

    private fun selectedImportant(boolean: Boolean) {
        if (boolean) {
            is_important_imageView.background =
                ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        } else {
            is_important_imageView.background =
                ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun showReminderPopUP() {
        dialog.setContentView(R.layout.calander_and_time_popup)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.calender.setBackgroundColor(Color.GRAY)
        dialog.cancel.setOnClickListener {
            NotificationSchedular.cancelReminder(
                applicationContext,
                AlarmReceiver::class.java,
                number
            )
            val editor = sharedPreferences.edit()
            editor.putString(textView_title.text.toString() + "date", "")
            editor.putString(textView_title.text.toString() + "time", "")
            editor.apply()
            val trueOrFalse = false
            selectReminder(trueOrFalse)
            dataViewModel.updateReminder(id, trueOrFalse)
            showDateAndTime()
            dialog.dismiss()
        }
        dialog.close_popup_calendar.setOnClickListener {
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
            NotificationSchedular.setReminder(
                applicationContext,
                AlarmReceiver::class.java,
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
                timePicker.currentHour,
                timePicker.currentMinute,
                textView_title.text.toString(),
                number
            )
            val editor = sharedPreferences.edit()
            editor.putString(
                textView_title.text.toString() + "date",
                datePicker.year.toString() + "/" + datePicker.month.toString() + "/" + datePicker.dayOfMonth.toString()
            )
            editor.putString(
                textView_title.text.toString() + "time",
                timePicker.currentHour.toString() + ":" + timePicker.currentMinute.toString()
            )
            editor.apply()
            val trueOrFalse = true
            selectReminder(trueOrFalse)
            dataViewModel.updateReminder(id, trueOrFalse)
            showDateAndTime()
            dialog.dismiss()
        }
    }

    private fun showDateAndTime() {
        date_textView.text =
            sharedPreferences.getString(textView_title.text.toString() + "date", "")
        time_textView.text =
            sharedPreferences.getString(textView_title.text.toString() + "time", "")
    }

    private fun selectReminder(boolean: Boolean) {
        if (boolean) {
            reminder.background =
                ContextCompat.getDrawable(this, R.drawable.recycle_view_item_another)
        } else {
            reminder.background = ContextCompat.getDrawable(this, R.drawable.recycle_view_item)
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

}
