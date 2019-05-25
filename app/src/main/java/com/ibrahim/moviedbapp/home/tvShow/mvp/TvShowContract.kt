package com.ibrahim.moviedbapp.home.tvShow.mvp

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv

interface TvShowContract {

    interface View : BaseContract.View{
        fun succesfullSetZipModel(zip: ZipModelTv?)
        fun succesfullSetupDrawerImage(zip: ZipModelTv?)
        fun succesfullValidateTypeScreen(zip: ZipModelTv?)
    }

    interface Presenter : BaseContract.ServicePresenter{
        fun getTvShow()
    }


}