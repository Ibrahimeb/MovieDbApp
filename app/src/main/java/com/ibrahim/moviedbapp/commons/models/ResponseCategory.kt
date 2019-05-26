package com.ibrahim.moviedbapp.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCategory(

	@field:SerializedName("genres")
	val genres: List<GenresItem>? = null
) : Parcelable