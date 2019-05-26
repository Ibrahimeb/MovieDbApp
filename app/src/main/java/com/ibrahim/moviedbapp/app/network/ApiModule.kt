package com.ibrahim.moviedbapp.app.network

import com.ibrahim.moviedbapp.app.di.AppScope
import com.ibrahim.moviedbapp.details.api.DetailsApi
import com.ibrahim.moviedbapp.home.movie.api.MovieApi
import com.ibrahim.moviedbapp.home.tvShow.api.TvShowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class ApiModule{

    @Provides
    @AppScope
    fun providerMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @AppScope
    fun providerTvShowApi(retrofit: Retrofit)  = retrofit.create(TvShowApi::class.java)

    @Provides
    @AppScope
    fun providerDetailsApi(retrofit: Retrofit)  = retrofit.create(DetailsApi::class.java)

}