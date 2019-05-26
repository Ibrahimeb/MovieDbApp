package com.ibrahim.moviedbapp.details.api

import com.ibrahim.moviedbapp.commons.enums.BundlesKey
import com.ibrahim.moviedbapp.details.models.ResponseVideos
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApi {


    @GET("movie/{movie_id}/similar")
    fun getSimilarMovie( @Path("movie_id")idMovie: String,@Query("api_key") apiKey: String):Observable<ResponseMovie>
    @GET("movie/{movie_id}/videos")
    fun getVideos( @Path("movie_id")idMovie: String,@Query("api_key") apiKey: String):Observable<ResponseVideos>



    @GET("tv/{tv_id}/similar")
    fun getSimilarTvShow(@Path("tv_id")idTv:String, @Query("api_key") apiKey: String):Observable<ResponseTvShow>


}