package com.eva.postify.feature_details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eva.postify.feature_details.TestData
import com.eva.postify.feature_details.TrampolineSchedulerRule
import com.eva.postify.feature_details.domain.DetailsFetcherUseCase
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel.DetailsState.ErrorState
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel.DetailsState.LoadingState
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Rule
import org.junit.Test

internal class DetailsViewModelTest {
    private val state = mock<Observer<DetailsViewModel.DetailsState>>()
    private val useCase = mock<DetailsFetcherUseCase>()
    private val viewModel = DetailsViewModel(useCase)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val schedulerRule = TrampolineSchedulerRule()


    @Test
    fun `test on details opened`() {
        whenever(useCase.fetchDetails("1")).thenReturn(Observable.just(TestData.details))
        viewModel.detailsState.observeForever(state)

        viewModel.onDetailsOpened("1")
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(
                DetailsViewModel.DetailsState.SuccessState(
                    TestData.details
                )
            )
        }
    }

    @Test
    fun `test error case`() {
        whenever(useCase.fetchDetails("1")).thenReturn(Observable.error(TestData.error))
        viewModel.detailsState.observeForever(state)
        viewModel.onDetailsOpened("1")
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(ErrorState(TestData.error))
        }
    }
}
