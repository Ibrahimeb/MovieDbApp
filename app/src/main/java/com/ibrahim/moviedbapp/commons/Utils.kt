package com.ibrahim.moviedbapp.commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import com.ibrahim.moviedbapp.app.App

object Utils {


    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun getString(string: Int): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            App.getAppContext().getString(string)
        else
            App.getAppContext().getString(string)

    }


}