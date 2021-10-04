package com.phaedra.cleanarchitecturesample.data.remote

import com.phaedra.cleanarchitecturesample.data.remote.dto.PostDto
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<PostDto>

}