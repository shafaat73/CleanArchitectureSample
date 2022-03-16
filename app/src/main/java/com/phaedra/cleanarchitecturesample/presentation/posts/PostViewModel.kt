package com.phaedra.cleanarchitecturesample.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.phaedra.cleanarchitecturesample.PostsQuery
import com.phaedra.cleanarchitecturesample.common.Resource
import com.phaedra.cleanarchitecturesample.domain.use_case.get_posts.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val apolloClient: ApolloClient
) : ViewModel() {
    val postList = MutableLiveData<PostState>()

    val getResponse = MutableLiveData<List<PostsQuery.Post>>()


    init {
        getPosts()
        getPostsApollo()
    }


    private fun getPostsApollo() {
        viewModelScope.launch {
            val postsQuery = apolloClient.query(PostsQuery()).execute()
            getResponse.postValue(postsQuery.data?.posts ?: emptyList())
        }

    }

    private fun getPosts() {

        getPostUseCase().onEach { result ->

            when (result) {
                is Resource.Success -> {
                    postList.value = PostState(posts = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    postList.value = PostState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    postList.value = PostState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }
}