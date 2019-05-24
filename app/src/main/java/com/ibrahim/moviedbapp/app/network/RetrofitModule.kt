package com.ibrahim.moviedbapp.app.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibrahim.moviedbapp.app.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RetrofitModule {

    @Provides
    @AppScope
    fun providerBaseUrl() = "https://api.themoviedb.org/3/"


    @Provides
    @AppScope
    fun providerGson(): Gson = GsonBuilder().create()

    @Provides
    @AppScope
    fun providerRetrofit(okHttpClient: OkHttpClient, gson: Gson, url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()


}