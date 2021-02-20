package com.eva.postify.feature_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eva.postify.feature_list.TestData
import com.eva.postify.feature_list.TrampolineSchedulerRule
import com.eva.postify.feature_list.domain.PostsFetcherUseCase
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel.PostsState.*
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Rule
import org.junit.Test

internal class PostsViewModelTest {

    private val state = mock<Observer<PostsViewModel.PostsState>>()
    private val action = mock<Observer<PostsViewModel.PostsAction>>()
    private val useCase = mock<PostsFetcherUseCase>()
    private val viewModel = PostsViewModel(useCase)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val schedulerRule = TrampolineSchedulerRule()

    @Test
    fun `test on view ready success`() {
        whenever(useCase.fetchPosts(0)).thenReturn(Observable.just(TestData.listOfPosts))
        viewModel.postsState.observeForever(state)

        viewModel.onViewReady()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(
                SuccessState(
                    0,
                    TestData.listOfPosts
                )
            )
        }
    }

    @Test
    fun `test error case`() {
        whenever(useCase.fetchPosts(0)).thenReturn(Observable.error(TestData.error))
        viewModel.postsState.observeForever(state)
        viewModel.onViewReady()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(ErrorState(TestData.error))
        }
    }

    @Test
    fun `test on refresh clicked`() {
        whenever(useCase.fetchPosts(0)).thenReturn(Observable.just(TestData.listOfPosts))
        viewModel.postsState.observeForever(state)

        viewModel.onRefreshClicked()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(
                SuccessState(
                    0,
                    TestData.listOfPosts
                )
            )
        }
    }

    @Test
    fun `test on item clicked`() {
        viewModel.postsAction.observeForever(action)
        viewModel.onItemClicked(TestData.listOfPosts[0])
        verify(action).onChanged(PostsViewModel.PostsAction.OpenDetailsAction(TestData.listOfPosts[0]))
    }

    @Test
    fun `test on bottom reached`() {
        whenever(useCase.fetchPosts(0)).thenReturn(Observable.just(TestData.listOfPosts))
        whenever(useCase.fetchPosts(1)).thenReturn(Observable.just(TestData.listOfPosts))
        viewModel.postsState.observeForever(state)

        viewModel.onViewReady()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(SuccessState(0, TestData.listOfPosts))
        }
        viewModel.onBottomReached()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(SuccessState(1, TestData.listOfPosts + TestData.listOfPosts))
        }

    }

    @Test
    fun `test all data received scenario`() {
        whenever(useCase.fetchPosts(0)).thenReturn(Observable.just(TestData.listOfPosts))
        whenever(useCase.fetchPosts(1)).thenReturn(Observable.just(emptyList()))
        viewModel.postsState.observeForever(state)

        viewModel.onViewReady()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(SuccessState(0, TestData.listOfPosts))
        }
        viewModel.onBottomReached()
        state.inOrder {
            verify().onChanged(LoadingState)
            verify(state).onChanged(SuccessState(1, TestData.listOfPosts, true))
        }
    }

}