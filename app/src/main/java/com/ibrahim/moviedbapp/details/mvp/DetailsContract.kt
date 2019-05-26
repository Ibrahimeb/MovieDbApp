package com.ibrahim.moviedbapp.details.mvp

import com.ibrahim.moviedbapp.commons.BaseContract
import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie
import com.ibrahim.moviedbapp.home.tvShow.models.ResponseTvShow

interface DetailsContract {

    interface View : BaseContract.View{
        fun succesfullSimilarMovie(responseMovie: ResponseMovie)
        fun succesfullSimilarTv(responseTvShow: ResponseTvShow)
        fun setGenrsList(string: String)
    }

    interface Presenter : BaseContract.ServicePresenter{

        fun getSimilarMovie(id:String)
        fun getSimilarTv(id: String)
        fun getGeners(listGeners:List<Int>,responseCategory: ResponseCategory)


    }

}