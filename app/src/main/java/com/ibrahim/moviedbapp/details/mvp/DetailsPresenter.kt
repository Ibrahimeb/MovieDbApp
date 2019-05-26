package com.ibrahim.moviedbapp.details.mvp

import android.annotation.SuppressLint
import android.util.Log
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.details.models.ZipDetailsMovie
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow
import java.util.*

class DetailsPresenter(private var view: DetailsContract.View?,private val model: DetailsModel) : DetailsContract.Presenter{
    val TAG = DetailsPresenter::class.java.simpleName

    @SuppressLint("CheckResult")
    override fun getSimilarMovie(id: String) {
        view?.showProgress(true)
        model.getSimilarMovie(id).subscribeWith(object : CallbackHandlingObserver<ZipDetailsMovie>(this,"similar movie"){
            override fun onSuccess(data: ZipDetailsMovie) {
                view?.showProgress(false)
                view?.succesfullSimilarMovie(data.responseSimiliares!!)
                view?.succesfullVideosMovie(data.responseVideos!!)

            }

        })
    }

    @SuppressLint("CheckResult")
    override fun getSimilarTv(id: String) {
        view?.showProgress(true)
        model.getSimiliarTv(id).subscribeWith(object : CallbackHandlingObserver<ResponseTvShow>(this,"similar tv"){
            override fun onSuccess(data: ResponseTvShow) {
                view?.showProgress(false)
                view?.succesfullSimilarTv(data)
            }
        })
    }

    override fun getGeners(listGeners: List<Int>, responseCategory: ResponseCategory){
        val listGenersString  = mutableListOf<String>()
        for (idgenr in listGeners){
            for (itemGenrs in responseCategory.genres!!){
                if (idgenr == itemGenrs.id)
                    listGenersString.add(itemGenrs.name!!)
            }
        }

        Log.i(TAG, "getGeners: -->${listGenersString.joinToString(separator = ",")}")
       view?.setGenrsList(listGenersString.joinToString(separator = ", "))

    }


    override fun onUnknownError(error: String, caller: String) {
        Log.e(TAG, "onUnknownError: error --> $error")
        genericError()
    }

    override fun onTimeoutError(caller: String) {
        Log.e(TAG, "onTimeoutError: ")
        genericError(R.string.error_timeout)
    }

    override fun onNetworkError(caller: String) {
        Log.e(TAG, "onNetworkError: ")
        genericError(R.string.error_network)
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        Log.e(TAG, "onBadRequestError: code error -->$codeError")
        genericError(R.string.error_badrequest)
    }

    override fun onServerError(caller: String) {
        Log.e(TAG, "onServerError: ")
        genericError(R.string.error_server)
    }

    private fun genericError(msg:Int = R.string.error_unknown){
        view?.showProgress(false)
        view?.makeToast(msg)
    }

    override fun onDetach() {
        view = null
    }

}