package com.ibrahim.moviedbapp.app.di

import android.content.Context
import com.ibrahim.moviedbapp.app.App
import com.ibrahim.moviedbapp.app.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides @AppScope fun providerApp()=app

    @Provides @AppScope @ApplicationContext fun providerAppContext(): Context = app


}