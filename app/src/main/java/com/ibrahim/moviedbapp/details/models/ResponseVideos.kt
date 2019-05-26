package com.ibrahim.moviedbapp.details.models

import com.google.gson.annotations.SerializedName

data class ResponseVideos(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)