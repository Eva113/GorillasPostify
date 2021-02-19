package com.eva.postify.data.repository

import com.eva.postify.data.model.PostDetails
import io.reactivex.rxjava3.core.Observable

interface DetailRepository {
    fun getPostDetails(id: String): Observable<PostDetails>
}