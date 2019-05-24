package com.ibrahim.moviedbapp.home.mvp

import android.annotation.SuppressLint
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.home.models.ResponseMoviePopular

class HomePresenter(var view:HomeContract.View? ,val homeModel: HomeModel):HomeContract.Presenter {



    override fun onUnknownError(error: String, caller: String) {
        view?.makeToast(R.string.abc_action_bar_home_description)
    }

    override fun onTimeoutError(caller: String) {
        view?.makeToast(R.string.abc_action_bar_home_description)
    }

    override fun onNetworkError(caller: String) {
        view?.makeToast(R.string.abc_action_bar_home_description)
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        view?.makeToast(R.string.abc_action_bar_home_description)
    }

    override fun onServerError(caller: String) {
        view?.makeToast(R.string.abc_action_bar_home_description)
    }



    override fun onDetach() {
        view = null
    }


    @SuppressLint("CheckResult")
    override fun getMovie() {
        homeModel.getPopularMovie().subscribeWith(object :CallbackHandlingObserver<ResponseMoviePopular>(this,"HomeModel"){
            override fun onSuccess(data: ResponseMoviePopular) {
                view?.makeToast(R.string.abc_action_bar_home_description)
            }

        })
    }






}