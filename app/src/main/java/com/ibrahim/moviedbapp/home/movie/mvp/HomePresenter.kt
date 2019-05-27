package com.ibrahim.moviedbapp.home.movie.mvp

import android.annotation.SuppressLint
import android.util.Log
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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
        Log.i(TAG, "getMovie: ")
        view?.showProgress(true)
        homeModel.getMovies().subscribeWith(object :CallbackHandlingObserver<ZipMovie>(this,"HomeModel"){
            override fun onSuccess(data: ZipMovie) {
                Log.i(TAG, "onSuccess: ")
                view?.showProgress(false)
                view?.succesfullSetZipModel(data)
                view?.succesfullSetupDrawerImage(data)
                view?.succesfullValidateTypeScreen(data)
                view?.succesfullSetCategory(data)
            }

        })
    }


    override fun filterListMovie(itemFilter: MutableList<Int>, listTarget: List<ResultsItem>) {
        Log.i(TAG, "filterListMovie: tamaño itemFilter ${itemFilter.size}")
        val listResult = mutableListOf<ResultsItem>()
        doAsync {
            for (item in listTarget){
                for (gener in item.genreIds!!){
                    for (id in itemFilter){
                        if (id == gener){
                            listResult.add(item)
                        }
                    }
                }
            }
            uiThread {
                Log.i(TAG, "filterListMovie: ${listResult.size}")
                if (itemFilter.size!=0)
                    view?.updateAdapter(listResult)
                else
                    view?.updateAdapter(listTarget)
            }
        }

    }
}