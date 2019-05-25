package com.ibrahim.moviedbapp.home.movie.mvp.movie

import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.home.movie.api.MovieApi
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class HomeModel(private val api: MovieApi) {

    fun getMovies(): Observable<ZipMovie> {

        val popularObservable: Observable<ResponseMovie> =
            api.getMoviePopular(Utils.getApiKey()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val topRateObservable: Observable<ResponseMovie> =
            api.getMovietopRated(Utils.getApiKey()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val upComingObservable: Observable<ResponseMovie> =
            api.getMovieUpComing(Utils.getApiKey()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        return Observable.zip(
            popularObservable,
            upComingObservable,
            topRateObservable,
            Function3{ popular:ResponseMovie,upComing:ResponseMovie,topRate:ResponseMovie -> ZipMovie(popular,upComing,topRate)})

    }
}

