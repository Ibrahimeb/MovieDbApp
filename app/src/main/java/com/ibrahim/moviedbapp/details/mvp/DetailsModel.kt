package com.ibrahim.moviedbapp.details.mvp

import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.details.api.DetailsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsModel(val api: DetailsApi) {


    fun getSimilarMovie(id: String) = api.getSimilarMovie(
        id,
        Utils.getApiKey()
    ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getSimiliarTv(id: String) = api.getSimilarTvShow(
        id,
        Utils.getApiKey()
    ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}