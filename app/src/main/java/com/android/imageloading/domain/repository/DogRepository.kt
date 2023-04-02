package com.android.imageloading.domain.repository

import com.android.imageloading.domain.model.Dog
import com.android.imageloading.utils.Resource

interface DogRepository {

    suspend fun getDogsImages(): Resource<List<Dog>>

}