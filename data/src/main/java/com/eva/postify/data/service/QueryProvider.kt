package com.eva.postify.data.service

import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import io.reactivex.rxjava3.core.Observable

internal interface QueryProvider {
    fun getPostsQuery(page: Int, limit: Int): Observable<PostsQuery.Data>

    fun getDetailsQuery(id: String): Observable<PostDetailsQuery.Data>
}