package com.android.imageloading.data.remote.model

import com.squareup.moshi.Json

data class Height(

	@Json(name="metric")
	val metric: String? = null,

	@Json(name="imperial")
	val imperial: String? = null
)