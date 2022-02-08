package com.phaedra.cleanarchitecturesample.domain.use_case.get_posts

import com.phaedra.cleanarchitecturesample.data.remote.dto.PostDto
import com.phaedra.cleanarchitecturesample.domain.repository.PostRepository

class FakeRepo : PostRepository {

    val list= mutableListOf<PostDto>()
    override suspend fun getPosts(): List<PostDto> {

        val data=PostDto("heheheheheh",1,"Data",12)
        val data1=PostDto("heheheheheh",2,"Data One",12)
        val data2=PostDto("heheheheheh",3,"Data Two",12)
        val data3=PostDto("heheheheheh",4,"Data Three",12)
        list.add(data)
        list.add(data1)
        list.add(data2)
        list.add(data3)
        return list
    }
}