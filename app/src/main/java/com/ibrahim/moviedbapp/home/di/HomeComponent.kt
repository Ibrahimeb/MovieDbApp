package com.ibrahim.moviedbapp.home.di

import com.ibrahim.moviedbapp.app.di.AppComponent
import com.ibrahim.moviedbapp.home.ui.HomeActivity
import dagger.Component
import dagger.Subcomponent

@HomeScope
@Subcomponent (modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}