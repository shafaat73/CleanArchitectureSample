package com.phaedra.cleanarchitecturesample.domain.use_case.get_posts

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


class GetPostUseCaseTest{

    private lateinit var getPostUseCase:GetPostUseCase
    private lateinit var fakeRepo: FakeRepo


    @Before
    fun setup(){
        fakeRepo= FakeRepo()
        getPostUseCase=GetPostUseCase(fakeRepo)
    }


    @Test
    fun checkData()= runBlocking{
        val notes = getPostUseCase().first()

        assertThat(notes.data!![0].title.equals("data"))
    }


}