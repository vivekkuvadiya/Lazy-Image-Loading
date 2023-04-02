package com.android.imageloading.data.remote.model

import com.squareup.moshi.Json

data class DogsResponse(

	@Json(name="DogsResponse")
	val dogsResponse: List<DogsResponseItem?>? = null
)