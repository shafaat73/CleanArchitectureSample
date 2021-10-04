package com.phaedra.cleanarchitecturesample.domain.use_case.get_posts

import com.phaedra.cleanarchitecturesample.common.Resource
import com.phaedra.cleanarchitecturesample.data.remote.dto.toPost
import com.phaedra.cleanarchitecturesample.domain.model.Post
import com.phaedra.cleanarchitecturesample.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading<List<Post>>())
            val coins = repository.getPosts().map { it.toPost() }
            emit(Resource.Success<List<Post>>(coins))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Post>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Post>>("Couldn't reach server. Check your internet connection."))
        }
    }

}