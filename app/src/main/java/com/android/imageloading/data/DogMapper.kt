package com.android.imageloading.data

import com.android.imageloading.data.remote.model.DogsResponse
import com.android.imageloading.domain.model.Dog

fun DogsResponse.toDogs(): List<Dog>? {
    return dogsResponse?.map { Dog(name = it?.name, url = it?.image?.url) }
}