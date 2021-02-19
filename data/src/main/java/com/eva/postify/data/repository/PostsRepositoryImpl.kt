package com.eva.postify.data.repository

import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.model.Post
import com.eva.postify.data.service.QueryProvider
import io.reactivex.rxjava3.core.Observable

internal class PostsRepositoryImpl(
    private val queryProvider: QueryProvider,
    private val mapper: Mapper
) : PostsRepository {

    override fun getPosts(page: Int, limit: Int): Observable<List<Post>> {
        return queryProvider.getPostsQuery(page, limit)
            .map {
                val nonNullList = it.posts?.data?.filterNotNull() ?: emptyList()
                mapper.mapPosts(nonNullList)
            }
    }
}