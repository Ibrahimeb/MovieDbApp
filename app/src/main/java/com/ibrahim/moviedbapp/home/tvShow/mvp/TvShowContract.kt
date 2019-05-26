package com.ibrahim.moviedbapp.home.tvShow.mvp

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.home.tvShow.models.ResultsItemTv
import com.ibrahim.moviedbapp.home.tvShow.models.ZipModelTv

interface TvShowContract {

    interface View : BaseContract.View{
        fun succesfullSetZipModel(zip: ZipModelTv?)
        fun succesfullSetupDrawerImage(zip: ZipModelTv?)
        fun succesfullValidateTypeScreen(zip: ZipModelTv?)
        fun succesfullSetCategory(zip: ZipModelTv?)

        fun updateAdapter(list: List<ResultsItemTv>)

    }

    interface Presenter : BaseContract.ServicePresenter{
        fun getTvShow()

        fun filterListTvShow(itemFilter: MutableList<Int>, listTarget: List<ResultsItemTv>)



    }


}