package com.ibrahim.moviedbapp.app.di

import com.ibrahim.moviedbapp.app.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent  {

    fun inject(app:App)



}