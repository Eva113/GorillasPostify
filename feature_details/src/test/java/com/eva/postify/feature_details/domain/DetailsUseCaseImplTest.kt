package com.eva.postify.feature_details.domain

import com.eva.postify.data.repository.DetailRepository
import com.eva.postify.feature_details.TestData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class DetailsUseCaseImplTest {

    private val repository = mock<DetailRepository>()
    private val useCase = DetailsFetcherUseCaseImpl(repository)

    @Test
    fun `test fetch details`() {
        whenever(repository.getPostDetails("1")).thenReturn(Observable.just(TestData.details))
        useCase.fetchDetails("1").test().assertValue(TestData.details)
    }


}