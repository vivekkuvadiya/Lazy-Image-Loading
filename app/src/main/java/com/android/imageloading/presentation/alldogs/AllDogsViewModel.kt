package com.android.imageloading.presentation.alldogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.imageloading.domain.model.Dog
import com.android.imageloading.domain.repository.DogRepository
import com.android.imageloading.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllDogsViewModel @Inject constructor(private val dogRepository: DogRepository) :ViewModel() {

    private val _dogs = MutableLiveData<Resource<List<Dog>>>()
    val dogs:LiveData<Resource<List<Dog>>> = _dogs

    init {
        getAllDogs()
    }

    private fun getAllDogs(){
        viewModelScope.launch {
            _dogs.postValue(Resource.Loading())
            when(val dogsResponse = dogRepository.getDogsImages()){
                is Resource.Success -> {
                    _dogs.postValue(Resource.Success(dogsResponse.data))
                }
                is Resource.Error -> {
                    _dogs.postValue(Resource.Error(message = dogsResponse.message))
                }
                else -> Unit
            }
        }
    }

}