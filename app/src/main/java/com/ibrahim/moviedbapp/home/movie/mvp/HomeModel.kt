package com.ibrahim.moviedbapp.home.movie.mvp

import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.home.movie.api.MovieApi
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers

class HomeModel(private val api: MovieApi) {

    fun getMovies(): Observable<ZipMovie> {

        val popularObservable: Observable<ResponseMovie> =
            api.getMoviePopular(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val topRateObservable: Observable<ResponseMovie> =
            api.getMovietopRated(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val upComingObservable: Observable<ResponseMovie> =
            api.getMovieUpComing(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val observableCategorys = api.getMovieCategory(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return Observable.zip(
            popularObservable,
            upComingObservable,
            topRateObservable,
            observableCategorys,
            Function4{ popular:ResponseMovie,upComing:ResponseMovie,topRate:ResponseMovie,categorys:ResponseCategory -> ZipMovie(popular,upComing,topRate,categorys)})

    }
}

