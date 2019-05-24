package com.ibrahim.moviedbapp.app.network

import com.ibrahim.moviedbapp.app.di.AppScope
import com.ibrahim.moviedbapp.home.api.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule{

    @Provides
    @AppScope
    fun providerMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

}