package com.ibrahim.moviedbapp.home.movie.mvp

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.movie.models.ResultsItem
import com.ibrahim.moviedbapp.home.movie.models.ZipMovie
import okhttp3.Response

interface HomeContract {

    interface View : BaseContract.View {
        fun succesfullSetZipModel(zip: ZipMovie?)
        fun succesfullSetupDrawerImage(zip: ZipMovie?)
        fun succesfullValidateTypeScreen(zip: ZipMovie?)
        fun succesfullSetCategory(zip: ZipMovie?)

        fun succesfullUpdatePopularMovie(popularMovie:ResponseMovie)

        fun showFooter(isShow:Boolean)

        fun updateAdapter(list: List<ResultsItem>)


    }

    interface Presenter : BaseContract.ServicePresenter {
        fun getZipMovie(currentPage:Int)
        fun getPopular(currentPage: Int)
        fun getTopRate(currentPage: Int)
        fun getUpcoming(currentPage: Int)
        fun filterListMovie(itemFilter:MutableList<Int>, listTarget: List<ResultsItem>)
    }


}