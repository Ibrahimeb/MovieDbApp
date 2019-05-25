package com.ibrahim.moviedbapp.home.di

import com.ibrahim.moviedbapp.home.api.MovieApi
import com.ibrahim.moviedbapp.home.mvp.movie.HomeContract
import com.ibrahim.moviedbapp.home.mvp.movie.HomeModel
import com.ibrahim.moviedbapp.home.mvp.movie.HomePresenter
import dagger.Module
import dagger.Provides


@Module
class HomeModule(val homeView: HomeContract.View) {

     @Provides
     @HomeScope
     fun prividerView() = this.homeView

    @Provides
    @HomeScope
    fun providerModel(api:MovieApi) = HomeModel(api)

    @Provides
    @HomeScope
    fun providesPresenter(homeView: HomeContract.View, model: HomeModel) =
        HomePresenter(homeView, model)




}