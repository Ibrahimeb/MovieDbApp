package com.ibrahim.moviedbapp.home.movie.mvp

import android.annotation.SuppressLint
import android.util.Log
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import com.ibrahim.moviedbapp.home.movie.mvp.movie.HomeContract
import com.ibrahim.moviedbapp.home.movie.mvp.movie.HomeModel

class HomePresenter(private var view: HomeContract.View?, private val homeModel: HomeModel):
    HomeContract.Presenter {
    val TAG = HomePresenter::class.java.simpleName


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
        view?.makeToast(R.string.error_badrequest)
    }

    override fun onServerError(caller: String) {
        Log.e(TAG, "onServerError: ")
        view?.makeToast(R.string.error_server)
    }

    private fun genericError(msg:Int = R.string.error_unknown){
        view?.showProgress(false)
        view?.makeToast(msg)
    }

    override fun onDetach() {
        view = null
    }


    @SuppressLint("CheckResult")
    override fun getMovie() {
        view?.showProgress(true)
        homeModel.getMovies().subscribeWith(object :CallbackHandlingObserver<ZipMovie>(this,"HomeModel"){
            override fun onSuccess(data: ZipMovie) {
                view?.showProgress(false)
                view?.succesfullSetupDrawerImage(data)
                view?.succesfullSetZipModel(data)
                view?.succesfullValidateTypeScreen(data)
            }

        })
    }






}