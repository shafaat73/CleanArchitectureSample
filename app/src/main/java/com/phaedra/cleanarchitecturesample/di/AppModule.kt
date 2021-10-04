package com.phaedra.cleanarchitecturesample.di

import com.phaedra.cleanarchitecturesample.common.Constants
import com.phaedra.cleanarchitecturesample.data.remote.ApiService
import com.phaedra.cleanarchitecturesample.data.repository.PostRepositoryImpl
import com.phaedra.cleanarchitecturesample.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiService): PostRepository {
        return PostRepositoryImpl(api)
    }
}