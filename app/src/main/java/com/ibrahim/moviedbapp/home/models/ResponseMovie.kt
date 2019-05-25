package com.ibrahim.moviedbapp.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.parceler.Parcel

@Parcelize
data class ResponseMovie(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
) : Parcelable