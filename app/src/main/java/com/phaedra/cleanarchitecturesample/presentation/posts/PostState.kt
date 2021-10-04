package com.phaedra.cleanarchitecturesample.presentation.posts

import com.phaedra.cleanarchitecturesample.domain.model.Post

data class PostState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
)