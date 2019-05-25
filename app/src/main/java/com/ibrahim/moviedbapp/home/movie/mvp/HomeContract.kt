package com.ibrahim.moviedbapp.home.movie.mvp.movie

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie

interface HomeContract {

    interface View : BaseContract.View {
        fun succesfullSetZipModel(zip: ZipMovie?)
        fun succesfullSetupDrawerImage(zip: ZipMovie?)
        fun succesfullValidateTypeScreen(zip: ZipMovie?)


    }

    interface Presenter : BaseContract.ServicePresenter {
        fun getMovie()
    }


}