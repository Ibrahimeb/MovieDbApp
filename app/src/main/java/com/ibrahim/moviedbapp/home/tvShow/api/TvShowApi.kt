package com.ibrahim.moviedbapp.home.tvShow.api

import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET("tv/popular")
    fun getTvPopular(@Query("api_key") apiKey:String): Observable<ResponseTvShow>

    @GET("tv/on_the_air")
    fun getTvOnAir(@Query("api_key") apiKey:String): Observable<ResponseTvShow>

    @GET("tv/top_rated")
    fun getTvtopRated(@Query("api_key") apiKey:String): Observable<ResponseTvShow>


}