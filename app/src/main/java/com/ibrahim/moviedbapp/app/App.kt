package com.ibrahim.moviedbapp.app

import android.app.Application
import com.ibrahim.moviedbapp.app.di.AppComponent
import com.ibrahim.moviedbapp.app.di.AppModule
import com.ibrahim.moviedbapp.app.di.DaggerAppComponent


class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}