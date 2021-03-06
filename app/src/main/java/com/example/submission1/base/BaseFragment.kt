package com.example.submission1.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.submission1.viewmodel.FavsVM


abstract class BaseFragment<viewmodel : InterVM> : Fragment(), BaseView {

    private val TAG = BaseFragment::class.java.simpleName

    private var activity: BaseView? = null

    protected  var viewmodel: viewmodel ?= null

    protected abstract fun initViewModel(): viewmodel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseView
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewmodel?.onDestroy()
    }

    override fun contextView(): Context = context as Context

    override fun onShowProgressbar() {}

    override fun onHideProgressbar() {}

    override fun onPushInformation(message: String?, data: Any?) {
        activity?.onPushInformation(message, data)
    }
}