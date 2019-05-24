package com.ibrahim.moviedbapp.home.mvp

import com.ibrahim.moviedbapp.home.api.MovieApi
import dagger.Module
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel(val api : MovieApi) {

    fun getPopularMovie() = api.getMoviePopular("e633d5667f2c7b610499bcce8643aa5c").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}