package com.example.submission1.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.submission1.R
import com.example.submission1.local.prefs.SettingPref
import com.example.submission1.reminder.DailyReminder
import com.example.submission1.reminder.ReleaseReminder
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

class SettingActivity : AppCompatActivity() {
    private lateinit var setting: SettingPref
    private lateinit var releaseRemainder: ReleaseReminder
    private lateinit var dailyRemainder: DailyReminder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setupData()
        setupAppearance()
    }

    private fun setupData() {
        setting = SettingPref(this)
        releaseRemainder = ReleaseReminder()
        dailyRemainder = DailyReminder()

        sw_daily_remainder.isChecked = setting.dailyRemainder
        sw_release_remainder.isChecked = setting.releaseRemainder
        tv_desc_language.text = Locale.getDefault().displayCountry
    }

    private fun setupAppearance() {
        layout_daily_remainder.setOnClickListener {
            sw_daily_remainder.isChecked = !sw_daily_remainder.isChecked
        }

        layout_release_remainder.setOnClickListener {
            sw_release_remainder.isChecked = !sw_release_remainder.isChecked
        }

        sw_release_remainder.setOnCheckedChangeListener { _, isChecked ->
            setting.releaseRemainder = isChecked

            if (isChecked)
                releaseRemainder.startReleaseReminder(this)
            else
                releaseRemainder.stopDailyReminder(this)
        }

        sw_daily_remainder.setOnCheckedChangeListener { _, isChecked ->
            setting.dailyRemainder = isChecked

            if (isChecked)
                dailyRemainder.startDailyReminder(this)
            else
                dailyRemainder.stopDailyReminder(this)
        }

        layout_language.setOnClickListener {
            startActivityForResult(Intent(Settings.ACTION_LOCALE_SETTINGS), 20)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20) {
            setResult(requestCode)
            finish()
        }
    }

}
