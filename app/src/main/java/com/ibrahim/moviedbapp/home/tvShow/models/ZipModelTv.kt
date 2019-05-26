package com.ibrahim.moviedbapp.home.tvShow.models

import com.ibrahim.moviedbapp.commons.models.ResponseCategory

data class ZipModelTv(
    val popularResponse: ResponseTvShow? = null,
    val topRateResponse: ResponseTvShow? = null,
    val lastedResponse: ResponseTvShow? = null,
    val categoryTvShow: ResponseCategory
)