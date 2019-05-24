package com.ibrahim.moviedbapp.app.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.commons.Utils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(var context: Context) : Interceptor {

    companion object {
        @JvmStatic
        fun isOnline(c: Context): Boolean {
            val connectivityManager: ConnectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.getActiveNetworkInfo() == null)
                return false
            val netInfo: NetworkInfo = connectivityManager.getActiveNetworkInfo();
            return (netInfo.isConnected())
        }
    }

    @Throws(IOException::class)
    override
    fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context)) {

        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }


}