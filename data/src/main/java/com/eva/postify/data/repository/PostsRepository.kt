package com.eva.postify.data.repository

import com.eva.postify.data.model.Post
import io.reactivex.rxjava3.core.Observable

interface PostsRepository {
    fun getPosts(page: Int, limit: Int): Observable<List<Post>>
}