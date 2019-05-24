package com.ibrahim.moviedbapp.commons

interface BaseContract {

    interface View{
        fun showProgress(isShow: Boolean)
        fun makeToast(msg:Int)
    }


    interface Presenter<T> {
        fun onAttach(view: T)
        fun onDetach()
    }


    interface ServicePresenter<T>:Presenter<T>{
        fun onUnknownError(error: String, caller: String)

        fun onTimeoutError(caller: String)

        fun onNetworkError(caller: String)

        fun onBadRequestError(caller: String, codeError: Int)

        fun onServerError(caller: String)

        fun infoError(cause: Throwable?, msg: String?)

    }

}