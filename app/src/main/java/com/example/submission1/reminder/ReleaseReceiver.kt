package com.example.submission1.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.submission1.api.ApiDbMovies
import com.example.submission1.api.Retro
import com.example.submission1.api.RxUtils
import com.example.submission1.model.Movie
import com.example.submission1.pages.MainActivity
import com.example.submission1.utils.NotifUtils
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*

class ReleaseReceiver : BroadcastReceiver() {
    private var subs: Disposable? = null
    private val ReleaseReminder = 102

    fun startReleaseReminder(context: Context) {
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, ReleaseReceiver::class.java)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val lateintent = PendingIntent.getBroadcast(context, ReleaseReminder, i, 0)
        alarm.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            lateintent
        )
    }

    fun stopDailyReminder(context: Context) {
        val i = Intent(context, ReleaseReceiver::class.java)
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val lateintent = PendingIntent.getBroadcast(context, ReleaseReminder, i, 0)
        lateintent.cancel()
        alarm.cancel(lateintent)
    }

    private fun sendNotif(context: Context, movie: Movie) {
        movie.original_title?.let {
            val intent = Intent(context, MainActivity::class.java)
            val message = movie.original_title + "telah rilis"
            NotifUtils.showStackNotification(
                context,
                it,
                message,
                ReleaseReminder, intent
            )
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val date = Calendar.getInstance().time
            val sDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)

            subs = Retro()
                .get()
                .create(ApiDbMovies::class.java)
                .getRelease(sDate, sDate, getLocale())
                .compose(RxUtils.applyObservableAsync())
                .subscribe {
                    it.results?.forEach { movie ->
                        sendNotif(context, movie)
                    }
                }
        }
    }

    private fun getLocale(): String {
        val lang = Locale.getDefault().language
        return if (lang == "in")
            "id"
        else lang
    }

}
