package com.android.imageloading.data.remote

import com.android.imageloading.data.remote.model.DogsResponse
import com.android.imageloading.data.remote.model.DogsResponseItem
import retrofit2.http.GET

interface DogApi {

    @GET("breeds")
    suspend fun getDogImages():Array<DogsResponseItem>

}