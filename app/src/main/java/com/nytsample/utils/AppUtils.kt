package com.nytsample.utils

import android.content.Context
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager



class AppUtils{

    companion object {

        const val BaseURL = "http://api.nytimes.com/"
        const val MiddleParams = "svc/mostpopular/v2/mostviewed/all-sections/"
        const val ApiKey = "25f99e1ab3f24b818537f926ab63e0dd"

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}