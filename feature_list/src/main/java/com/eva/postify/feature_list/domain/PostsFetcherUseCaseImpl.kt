package com.eva.postify.feature_list.domain

import com.eva.postify.data.model.Post
import com.eva.postify.data.repository.PostsRepository
import io.reactivex.rxjava3.core.Observable

internal class PostsFetcherUseCaseImpl(private val repository: PostsRepository) :
    PostsFetcherUseCase {
    override fun fetchPosts(page: Int, limit: Int): Observable<List<Post>> {
        return repository.getPosts(page, limit)
    }

}