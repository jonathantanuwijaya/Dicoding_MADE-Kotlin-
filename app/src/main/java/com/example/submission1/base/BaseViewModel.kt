package com.example.submission1.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.submission1.R
import com.example.submission1.utils.Retro
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.json.JSONObject
import retrofit2.HttpException
import java.util.*

abstract class BaseViewModel : ViewModel(), InterVM {
    private val TAG = BaseViewModel::class.java.simpleName

    protected var subscriber: Disposable? = null

    protected var view: BaseView? = null

    inline fun <reified api> api() : api = Retro().get().create(api::class.java)

    abstract fun onResponseSuccess(data: Any)

    fun setupView(view: BaseView) {
        this.view = view
    }

    override fun onDestroy() {
        subscriber?.dispose()
    }

    fun getLocale() : String {
        val lang = Locale.getDefault().language
        return if (lang == "in")
            "id"
        else lang
    }

    fun onSuccess(): Consumer<Any> =
            Consumer {
                view?.onHideProgressbar()
                onResponseSuccess(it)
            }

    fun onFailed(): Consumer<Any> =
            Consumer {
                view?.onHideProgressbar()

                when (it) {
                    is HttpException -> {
                        try {
                            val errBody = it.response()?.errorBody()?.string()
                            val json = JSONObject(errBody)
                            view?.onPushInformation(json["status_message"].toString(), null)
                        } catch (e: Exception) {
                            Log.e(TAG, e.message)
                            view?.onPushInformation(view?.contextView()?.getString(R.string.server), null)
                        }
                    }
                    else -> {
                        val connectivityManager = view?.contextView()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                        if (isConnected) {
                            view?.onPushInformation(view?.contextView()?.getString(R.string.internet), null)
                        } else {
                            view?.onPushInformation(view?.contextView()?.getString(R.string.errorconnect), null)
                        }
                    }
                }
            }
}