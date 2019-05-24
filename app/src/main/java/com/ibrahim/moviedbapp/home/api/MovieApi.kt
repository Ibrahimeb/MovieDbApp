package com.ibrahim.moviedbapp.home.api

import com.ibrahim.moviedbapp.home.models.ResponseMoviePopular
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMoviePopular(@Query("api_key") apiKey:String):Observable<ResponseMoviePopular>

}