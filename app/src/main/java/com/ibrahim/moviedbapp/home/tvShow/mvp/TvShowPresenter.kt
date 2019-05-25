package com.ibrahim.moviedbapp.home.tvShow.mvp

import android.annotation.SuppressLint
import android.util.Log
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv

class TvShowPresenter(private var view:TvShowContract.View?, private val model:TvShowModel) : TvShowContract.Presenter {
    val TAG = TvShowPresenter::class.java.simpleName

    @SuppressLint("CheckResult")
    override fun getTvShow() {
        view?.showProgress(true)
        model.getData().subscribeWith(object : CallbackHandlingObserver<ZipModelTv>(this,"ZIP TV SHOW"){
            override fun onSuccess(data: ZipModelTv) {
                view?.showProgress(false)
                view?.succesfullSetZipModel(data)
                view?.succesfullSetupDrawerImage(data)
                view?.succesfullValidateTypeScreen(data)
            }

        })


    }

    override fun onDetach() {
        view = null
    }

    override fun onUnknownError(error: String, caller: String) {
        Log.e(TAG, "onUnknownError: error--> $error ")
        genericError()
    }

    override fun onTimeoutError(caller: String) {
        Log.e(TAG, "onTimeoutError: ")
        genericError(R.string.error_timeout)
    }

    override fun onNetworkError(caller: String) {
        Log.e(TAG, "onNetworkError:  ")
        genericError(R.string.error_network)
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        Log.e(TAG, "onBadRequestError: $codeError")
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

}