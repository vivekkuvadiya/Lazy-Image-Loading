package com.android.imageloading.data.repository

import com.android.imageloading.data.remote.DogApi
import com.android.imageloading.data.toDogs
import com.android.imageloading.domain.model.Dog
import com.android.imageloading.domain.repository.DogRepository
import com.android.imageloading.utils.Resource
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor (private val dogApi: DogApi): DogRepository {

    override suspend fun getDogsImages(): Resource<List<Dog>> {
        return try {
            val response = dogApi.getDogImages()
            Resource.Success(response.map { Dog(name = it.name, url = it.image?.url) })
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(message =  e.message?:"An unknown error occurred.")
        }
    }

}