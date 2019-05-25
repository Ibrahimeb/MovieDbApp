package com.ibrahim.moviedbapp.home.movie.api

import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMoviePopular(@Query("api_key") apiKey:String):Observable<ResponseMovie>

    @GET("movie/upcoming")
    fun getMovieUpComing(@Query("api_key") apiKey:String):Observable<ResponseMovie>

    @GET("movie/top_rated")
    fun getMovietopRated(@Query("api_key") apiKey:String):Observable<ResponseMovie>

}