package com.eva.postify.data.repository

import com.eva.postify.TestData
import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.service.QueryProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

internal class PostsRepositoryImplTest {
    private val queryProvider = mock<QueryProvider>()
    private val mapper = mock<Mapper>()
    private val repository = PostsRepositoryImpl(queryProvider, mapper)

    @Test
    fun `test getting posts`() {
        whenever(queryProvider.getPostsQuery(0, 10)).thenReturn(Observable.just(TestData.postsRepsonseData))
        whenever(mapper.mapPosts(TestData.listOfPostModels)).thenReturn(TestData.listOfPosts)
        repository.getPosts(0, 10).test().assertValue(TestData.listOfPosts)
    }

}