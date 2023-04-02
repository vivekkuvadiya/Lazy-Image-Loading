package com.android.imageloading.data.remote.model

import com.squareup.moshi.Json

data class Image(

	@Json(name="width")
	val width: Int? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="height")
	val height: Int? = null
)