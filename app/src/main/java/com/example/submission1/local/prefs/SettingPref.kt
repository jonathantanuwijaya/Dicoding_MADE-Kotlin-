package com.example.submission1.local.prefs

import android.content.Context
import android.content.SharedPreferences

class SettingPref(val context: Context) {

    private var pref: SharedPreferences = context.getSharedPreferences(context.packageName, 0)

    var dailyRemainder: Boolean
        get() = pref.getBoolean(prefsReminder, false)
        set(value) {
            pref.edit().putBoolean(prefsReminder, value).apply()
        }

    var releaseRemainder: Boolean
        get() = pref.getBoolean(prefsRelease, false)
        set(value) {
            pref.edit().putBoolean(prefsRelease, value).apply()
        }

    companion object {
        const val prefsReminder = "pref_daily_remainder"
        const val prefsRelease = "pref_release_remainder"
    }
}