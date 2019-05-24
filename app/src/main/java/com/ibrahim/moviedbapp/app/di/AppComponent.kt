package com.ibrahim.moviedbapp.app.di

import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.app.network.ApiModule
import com.ibrahim.moviedbapp.home.api.MovieApi
import com.ibrahim.moviedbapp.home.di.HomeComponent
import com.ibrahim.moviedbapp.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class , ApiModule::class])
interface AppComponent  {

    fun inject(app:App)

     fun plus(homeModule : HomeModule):HomeComponent

//    fun movieApi() : MovieApi



}