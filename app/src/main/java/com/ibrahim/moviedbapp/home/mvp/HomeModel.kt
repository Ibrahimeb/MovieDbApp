package com.ibrahim.moviedbapp.home.mvp

import com.ibrahim.moviedbapp.home.api.MovieApi
import com.ibrahim.moviedbapp.home.models.ResponseMoviePopular
import dagger.Module
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel(private val api : MovieApi) {

    fun getPopularMovie(): Observable<ResponseMoviePopular> = api.getMoviePopular("e633d5667f2c7b610499bcce8643aa5c").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}