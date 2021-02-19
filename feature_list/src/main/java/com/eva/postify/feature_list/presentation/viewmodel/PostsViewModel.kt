package com.eva.postify.feature_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eva.postify.data.model.Post
import com.eva.postify.feature_list.domain.PostsFetcherUseCase
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel.PostsState.LoadingState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

internal class PostsViewModel(private val domain: PostsFetcherUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val mutablePostsState = MutableLiveData<PostsState>()
    val postsState: LiveData<PostsState>
        get() = mutablePostsState

    private val mutablePostsAction = MutableLiveData<PostsAction>()
    val postsAction: LiveData<PostsAction>
        get() = mutablePostsAction

    sealed class PostsState {
        data class SuccessState(val currentPage: Int, val data: List<Post>) : PostsState()
        object LoadingState : PostsState()
        data class ErrorState(val error: Throwable) : PostsState()
    }

    sealed class PostsAction {
        data class OpenDetailsAction(val post: Post) : PostsAction()
        object None : PostsAction()
    }

    fun onViewReady() {
        if (postsState.value !is PostsState.SuccessState) {
            fetchPosts(0)
        }
        mutablePostsAction.value = PostsAction.None
    }

    fun onRefreshClicked() {
        fetchPosts(0)
    }

    fun onBottomReached() {
        val currentState = mutablePostsState.value
        if (currentState is PostsState.SuccessState) {
            fetchPosts(currentState.currentPage + 1, currentState.data)
        } else {
            fetchPosts(0)
        }
    }

    fun onItemClicked(post: Post) {
        mutablePostsAction.value = PostsAction.OpenDetailsAction(post)
    }

    private fun fetchPosts(page: Int, previousList: List<Post> = emptyList()) {
        mutablePostsState.value = LoadingState
        compositeDisposable.add(domain.fetchPosts(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<PostsState> {
                PostsState.SuccessState(page, previousList + it)
            }
            .onErrorReturn(PostsState::ErrorState)
            .subscribe(mutablePostsState::setValue))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}