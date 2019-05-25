package com.ibrahim.moviedbapp.home.tvShow.di

import com.ibrahim.moviedbapp.home.tvShow.api.TvShowApi
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowContract
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowModel
import com.ibrahim.moviedbapp.home.tvShow.mvp.TvShowPresenter
import dagger.Module
import dagger.Provides

@Module
class TvshowModule(val view:TvShowContract.View){

    @Provides
    @TvShowScope
    fun providerView() = view

    @Provides
    @TvShowScope
    fun providerModel(api:TvShowApi) = TvShowModel(api)

    @Provides
    @TvShowScope
    fun providerPresenter(view:TvShowContract.View,model: TvShowModel)=TvShowPresenter(view,model)


}