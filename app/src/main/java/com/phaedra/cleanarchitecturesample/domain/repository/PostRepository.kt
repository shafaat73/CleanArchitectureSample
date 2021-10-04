package com.phaedra.cleanarchitecturesample.domain.repository

import com.phaedra.cleanarchitecturesample.data.remote.dto.PostDto

interface PostRepository {

    suspend fun getPosts(): List<PostDto>
}