package com.eva.postify.feature_details.domain

import com.eva.postify.data.model.PostDetails
import io.reactivex.rxjava3.core.Observable

internal interface DetailsFetcherUseCase {
    fun fetchDetails(id: String): Observable<PostDetails>
}