package com.ibrahim.moviedbapp.home.di

import com.ibrahim.moviedbapp.home.api.MovieApi
import com.ibrahim.moviedbapp.home.mvp.HomeContract
import com.ibrahim.moviedbapp.home.mvp.HomeModel
import com.ibrahim.moviedbapp.home.mvp.HomePresenter
import com.ibrahim.moviedbapp.home.ui.HomeActivity
import dagger.Module
import dagger.Provides


@Module
class HomeModule {


    @Provides
    @HomeScope
    fun providerModel(api:MovieApi) = HomeModel(api)

    @Provides
    @HomeScope
    fun providesPresenter(model:HomeModel) = HomePresenter(model)




}