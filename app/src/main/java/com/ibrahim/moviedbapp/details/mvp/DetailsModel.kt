package com.ibrahim.moviedbapp.details.mvp

import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.details.api.DetailsApi
import com.ibrahim.moviedbapp.details.models.ZipDetailsMovie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class DetailsModel(val api: DetailsApi) {


    fun getSimilarMovie(id: String): Observable<ZipDetailsMovie> {
      val observableSimilarVideos =  api.getSimilarMovie(
            id,
            Utils.getApiKey()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        val observableVideos =  api.getVideos(
            id,
            Utils.getApiKey()
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        return Observable.zip(observableSimilarVideos,observableVideos, BiFunction(){similares,videos -> ZipDetailsMovie(similares,videos)})
    }

    fun getSimiliarTv(id: String) = api.getSimilarTvShow(
        id,
        Utils.getApiKey()
    ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}
