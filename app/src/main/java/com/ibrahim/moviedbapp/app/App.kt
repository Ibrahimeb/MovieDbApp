package com.ibrahim.moviedbapp.app

import android.app.Application
import android.content.Context
import com.ibrahim.moviedbapp.app.di.AppComponent
import com.ibrahim.moviedbapp.app.di.AppModule
import com.ibrahim.moviedbapp.app.di.DaggerAppComponent


class App : Application() {


    companion object {

        @JvmStatic
        lateinit var INSTANCE: App

        @JvmStatic
        fun get(): App {
            return INSTANCE
        }

        @JvmStatic
        fun getAppContext(): Context {
            return INSTANCE.getBaseContext()
        }
    }



    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }



    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component.inject(this)
    }

}