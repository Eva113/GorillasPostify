package com.eva.postify.data.repository

import com.eva.postify.TestData
import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.service.QueryProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class DetailRepositoryImplTest {
    private val queryProvider = mock<QueryProvider>()
    private val mapper = mock<Mapper>()
    private val repository = DetailRepositoryImpl(queryProvider, mapper)

    @Test
    fun `test getting post details`() {
        whenever(queryProvider.getDetailsQuery("1")).thenReturn(Observable.just(TestData.postDetailResponseData))
        whenever(mapper.mapDetails(TestData.postDetailsModel)).thenReturn(TestData.postDetails)
        repository.getPostDetails("1").test().assertValue(TestData.postDetails)
    }
}