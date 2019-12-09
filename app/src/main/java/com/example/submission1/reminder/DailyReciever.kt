package com.example.submission1.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.submission1.pages.MainActivity
import com.example.submission1.utils.NotifUtils
import java.util.*

class DailyReciever : BroadcastReceiver() {
    private val DAILY_REMINDER = 100
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { sendNotif(it) }
    }

    fun startDailyReminder(context: Context) {
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, DailyReciever::class.java)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val lateintent = PendingIntent.getBroadcast(context, DAILY_REMINDER, i, 0)
        alarm.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            lateintent
        )
    }

    fun stopDailyReminder(context: Context) {
        val i = Intent(context, DailyReciever::class.java)
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val delayintent = PendingIntent.getBroadcast(context, DAILY_REMINDER, i, 0)
        delayintent.cancel()
        alarm.cancel(delayintent)
    }

    private fun sendNotif(context: Context) {
        val i = Intent(context, MainActivity::class.java)
        NotifUtils.showNotification(
            context,
            "Submission 5",
            "Movie Catalogue",
            DAILY_REMINDER,
            i

        )
    }
}
