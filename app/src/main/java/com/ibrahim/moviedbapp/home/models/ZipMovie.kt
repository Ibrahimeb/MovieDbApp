package com.ibrahim.moviedbapp.home.models


data class ZipMovie(
    val popularList:ResponseMovie,
    val upComingList:ResponseMovie,
    val topRateList: ResponseMovie
)