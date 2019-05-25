package com.ibrahim.moviedbapp.commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import com.ibrahim.moviedbapp.app.App
import java.text.SimpleDateFormat
import java.util.*

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

    fun makeToast(msg:String,context: Context){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun getImageUrlMedium(path:String) = "https://image.tmdb.org/t/p/w300$path"
    fun getImageUrlLarge(path:String) = "https://image.tmdb.org/t/p/w500$path"
    fun getApiKey() = "e633d5667f2c7b610499bcce8643aa5c"

    fun getRamdonInt(size:Int) = Math.random()*size

    fun parseStringDate(date:String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.parse(date)
    }

    fun getYear(yourdate: Date):Int{
        val c = Calendar.getInstance()
        c.time = yourdate // yourdate is an object of type Date
        return c.get(Calendar.YEAR)
    }


}