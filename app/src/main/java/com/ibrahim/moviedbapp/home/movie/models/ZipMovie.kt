package com.ibrahim.moviedbapp.home.movie.models

import com.ibrahim.moviedbapp.commons.models.ResponseCategory
import kotlinx.android.parcel.Parcelize

data class ZipMovie(
    val popularList:ResponseMovie,
    val upComingList:ResponseMovie,
    val topRateList: ResponseMovie,
    val categoryMovie:ResponseCategory
)