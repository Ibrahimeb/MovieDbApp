package com.ibrahim.moviedbapp.details.models

import com.ibrahim.moviedbapp.home.movie.models.ResponseMovie

data class ZipDetailsMovie(
    val responseSimiliares:ResponseMovie?=null,
    val responseVideos: ResponseVideos?=null
)