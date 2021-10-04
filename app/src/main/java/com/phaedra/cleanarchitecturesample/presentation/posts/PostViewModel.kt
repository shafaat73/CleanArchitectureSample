package com.phaedra.cleanarchitecturesample.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phaedra.cleanarchitecturesample.common.Resource
import com.phaedra.cleanarchitecturesample.domain.model.Post
import com.phaedra.cleanarchitecturesample.domain.use_case.get_posts.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
):ViewModel() {
    val postList = MutableLiveData<PostState>()


    init {
        getPosts()
    }


    private fun getPosts(){

        getPostUseCase().onEach {result->

            when(result){
                is Resource.Success->{
                    postList.value=PostState(posts = result.data ?: emptyList())
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