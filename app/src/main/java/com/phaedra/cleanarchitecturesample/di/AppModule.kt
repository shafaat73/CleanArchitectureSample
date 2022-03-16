package com.phaedra.cleanarchitecturesample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.phaedra.cleanarchitecturesample.common.Constants
import com.phaedra.cleanarchitecturesample.data.remote.ApiService
import com.phaedra.cleanarchitecturesample.data.repository.PostRepositoryImpl
import com.phaedra.cleanarchitecturesample.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
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
    fun provideOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(
                        "authorization",
                        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InNoYWZhYXRAZ21haWwuY29tIiwiX2lkIjoiNjIwMjVlODM0OTljMzY2ZjJhZjFlMzg3IiwiaWF0IjoxNjQ0MzIyNDM1fQ.rsb5mOWqfr6glOntkQInWLBsEfVdQwUxNRqPAQ47h8M"
                    )
                    .build()
                chain.proceed(newRequest)
            })
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiService): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    fun provideApolloClient(
        okHttpClient: OkHttpClient
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://social.purzey.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
}