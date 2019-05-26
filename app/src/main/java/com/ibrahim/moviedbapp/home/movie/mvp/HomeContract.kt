package com.ibrahim.moviedbapp.home.movie.mvp

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie

interface HomeContract {

    interface View : BaseContract.View {
        fun succesfullSetZipModel(zip: ZipMovie?)
        fun succesfullSetupDrawerImage(zip: ZipMovie?)
        fun succesfullValidateTypeScreen(zip: ZipMovie?)
        fun succesfullSetCategory(zip: ZipMovie?)

        fun updateAdapter(list: List<ResultsItem>)


    }

    interface Presenter : BaseContract.ServicePresenter {
        fun getMovie()
        fun filterListMovie(itemFilter:MutableList<Int>, listTarget: List<ResultsItem>)
    }


}