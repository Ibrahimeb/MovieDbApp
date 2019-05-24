package com.ibrahim.moviedbapp.app.di

import android.content.Context
import com.ibrahim.moviedbapp.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides @Singleton fun providerApp()=app

    @Provides fun providerAppContext(): Context = app


}