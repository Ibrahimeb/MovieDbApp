package com.ibrahim.moviedbapp.commons

interface BaseContract {

    interface View{
        fun showProgress(isShow: Boolean)
        fun makeToast(msg:Int)
    }


    interface Presenter {

        fun onDetach()
    }


    interface ServicePresenter:Presenter{
        fun onUnknownError(error: String, caller: String)

        fun onTimeoutError(caller: String)

        fun onNetworkError(caller: String)

        fun onBadRequestError(caller: String, codeError: Int)

        fun onServerError(caller: String)



    }

}