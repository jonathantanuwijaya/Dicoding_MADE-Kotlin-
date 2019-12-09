package com.example.submission1.pages

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.submission1.R
import com.example.submission1.base.BaseActivity
import com.example.submission1.local.prefs.SettingPref
import com.example.submission1.reminder.DailyReciever
import com.example.submission1.reminder.ReleaseReceiver
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : BaseActivity<MovieVM>() {
    private lateinit var setting: SettingPref
    private lateinit var releaseRemainder: ReleaseReceiver
    private lateinit var dailyRemainder: DailyReciever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setupData()
        setupAppearance()
    }

    private fun setupData() {
        setting = SettingPref(this)
        releaseRemainder = ReleaseReceiver()
        dailyRemainder = DailyReciever()

        sw_daily_remainder.isChecked = setting.dailyRemainder
        sw_release_remainder.isChecked = setting.releaseRemainder
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20) {
            setResult(requestCode)
            finish()
        }
    }

    override fun initViewModel(): MovieVM =
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieVM::class.java)

}
