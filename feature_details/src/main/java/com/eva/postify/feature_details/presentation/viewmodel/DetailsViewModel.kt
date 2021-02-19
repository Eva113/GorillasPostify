package com.eva.postify.feature_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eva.postify.data.model.PostDetails
import com.eva.postify.feature_details.domain.DetailsFetcherUseCase
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel.DetailsState.LoadingState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

internal class DetailsViewModel(private val domain: DetailsFetcherUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val mutableDetailsState = MutableLiveData<DetailsState>()
    val detailsState: LiveData<DetailsState>
        get() = mutableDetailsState

    sealed class DetailsState {
        data class SuccessState(val details: PostDetails) : DetailsState()
        object LoadingState : DetailsState()
        data class ErrorState(val error: Throwable) : DetailsState()
    }

    fun onDetailsOpened(id: String) {
        fetchDetails(id)
    }

    private fun fetchDetails(id: String) {
        mutableDetailsState.value = LoadingState
        compositeDisposable.add(domain.fetchDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<DetailsState> {
                DetailsState.SuccessState(it)
            }
            .onErrorReturn(DetailsState::ErrorState)
            .subscribe(mutableDetailsState::setValue))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}