package com.example.submission1.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<viewmodel : InterVM> : AppCompatActivity(), IBaseView {
    override fun contextView(): Context = this

    private val TAG = BaseActivity::class.java.simpleName

    protected lateinit var viewmodel: viewmodel

    protected abstract fun initViewModel(): viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = initViewModel()
    }

    override fun onDestroy() {
        viewmodel.onDestroy()
        super.onDestroy()
    }

    override fun onPushInformation(message: String?, data: Any?) {
        message?.let { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
    }

    override fun onShowProgressbar() {}

    override fun onHideProgressbar() {}

}