package com.phaedra.cleanarchitecturesample.data.repository

import com.phaedra.cleanarchitecturesample.data.remote.ApiService
import com.phaedra.cleanarchitecturesample.data.remote.dto.PostDto
import com.phaedra.cleanarchitecturesample.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api:ApiService
):PostRepository {
    override suspend fun getPosts(): List<PostDto> {
        return api.getPosts()
    }
}