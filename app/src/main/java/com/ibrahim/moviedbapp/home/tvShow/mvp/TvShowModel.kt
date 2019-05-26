package com.ibrahim.moviedbapp.home.tvShow.mvp

import com.ibrahim.moviedbapp.commons.Utils
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.home.tvShow.api.TvShowApi
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers

class TvShowModel(val api: TvShowApi) {

    fun getData(): Observable<ZipModelTv> {
        val observablePopular =
            api.getTvPopular(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        val observableToRate =
            api.getTvtopRated(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        val observableLasted =
            api.getTvOnAir(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        val observableCategorys = api.getTvShowCategory(Utils.getApiKey(),Utils.getLanguaje()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return Observable.zip(
            observablePopular,
            observableLasted,
            observableToRate,
            observableCategorys,
            Function4{ popular: ResponseTvShow, lasted: ResponseTvShow, toprate: ResponseTvShow,categorys: ResponseCategory ->
                ZipModelTv(
                    popular,
                    toprate,
                    lasted,
                    categorys
                )
            })
    }

}