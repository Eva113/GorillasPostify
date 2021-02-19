package com.eva.postify.feature_details.domain

import com.eva.postify.data.model.PostDetails
import com.eva.postify.data.repository.DetailRepository
import io.reactivex.rxjava3.core.Observable

internal class DetailsFetcherUseCaseImpl(private val repository: DetailRepository) :
    DetailsFetcherUseCase {
    override fun fetchDetails(id: String): Observable<PostDetails> {
        return repository.getPostDetails(id)
    }
}