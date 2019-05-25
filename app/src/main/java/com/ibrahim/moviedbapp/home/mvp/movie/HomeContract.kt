package com.ibrahim.moviedbapp.home.mvp.movie

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.models.ZipMovie

interface HomeContract {

    interface View:BaseContract.View{
    fun succesfullRequest(zip:ZipMovie?)

    }

    interface Presenter:BaseContract.ServicePresenter{
        fun getMovie()
    }



}