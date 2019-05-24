package com.ibrahim.moviedbapp.home.mvp

import com.ibrahim.moviedbapp.commons.BaseContract

interface HomeContract {

    interface View:BaseContract.View

    interface Presenter:BaseContract.ServicePresenter{
        fun getMovie()
    }



}