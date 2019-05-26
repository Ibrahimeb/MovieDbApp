package com.ibrahim.moviedbapp.home.movie.di

import com.ibrahim.moviedbapp.home.movie.api.MovieApi
import com.ibrahim.moviedbapp.home.movie.mvp.HomeContract
import com.ibrahim.moviedbapp.home.movie.mvp.HomeModel
import com.ibrahim.moviedbapp.home.movie.mvp.HomePresenter
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