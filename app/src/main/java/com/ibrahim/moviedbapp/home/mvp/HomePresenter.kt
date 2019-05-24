package com.ibrahim.moviedbapp.home.mvp

import android.annotation.SuppressLint
import com.ibrahim.moviedbapp.R
import com.ibrahim.moviedbapp.app.network.CallbackHandlingObserver
import com.ibrahim.moviedbapp.home.models.ResponseMoviePopular
import kotlinx.android.synthetic.main.activity_main.*

class HomePresenter(val homeModel: HomeModel):HomeContract.Presenter {

   lateinit var view: HomeContract.View

    @SuppressLint("CheckResult")
    override fun getMovie() {
        homeModel.getPopularMovie().subscribeWith(object :CallbackHandlingObserver<ResponseMoviePopular>(this,"HomeModel"){
            override fun onSuccess(data: ResponseMoviePopular) {
                view.makeToast(R.string.abc_action_bar_home_description)
            }

        })
    }

    override fun onAttach(view: HomeContract.View) {
        this.view = view
    }

    override fun onDetach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUnknownError(error: String, caller: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTimeoutError(caller: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNetworkError(caller: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerError(caller: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun infoError(cause: Throwable?, msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}