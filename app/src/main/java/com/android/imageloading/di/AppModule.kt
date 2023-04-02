package com.android.imageloading.di

import com.android.imageloading.data.remote.DogApi
import com.android.imageloading.data.repository.DogRepositoryImpl
import com.android.imageloading.domain.repository.DogRepository
import com.android.imageloading.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDogApi() =
        Retrofit.Builder()
            .baseUrl(Constant.DOG_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DogApi::class.java)

    @Singleton
    @Provides
    fun provideDogsRepository(dogApi: DogApi):DogRepository = DogRepositoryImpl(dogApi)

}