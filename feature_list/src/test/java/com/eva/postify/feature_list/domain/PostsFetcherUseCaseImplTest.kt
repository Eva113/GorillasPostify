package com.eva.postify.feature_list.domain

import com.eva.postify.data.repository.PostsRepository
import com.eva.postify.feature_list.TestData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

internal class PostsFetcherUseCaseImplTest {
    private val repository = mock<PostsRepository>()
    private val useCase = PostsFetcherUseCaseImpl(repository)

    @Test
    fun `test fetch posts`() {
        whenever(repository.getPosts(0, 10)).thenReturn(Observable.just(TestData.listOfPosts))
        useCase.fetchPosts(0).test().assertValue(TestData.listOfPosts)
    }

}
