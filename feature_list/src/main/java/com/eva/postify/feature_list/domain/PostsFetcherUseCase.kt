package com.eva.postify.feature_list.domain

import com.eva.postify.data.model.Post
import io.reactivex.rxjava3.core.Observable

interface PostsFetcherUseCase {
    fun fetchPosts(page: Int, limit: Int = 10): Observable<List<Post>>
}