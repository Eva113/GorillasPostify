package com.eva.postify.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.rxQuery
import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.model.Post
import com.eva.postify.data.repository.PostsRepository
import com.eva.postify.data.service.QueryProvider
import io.reactivex.rxjava3.core.Observable

internal class PostsRepositoryImpl(
    private val apolloClient: ApolloClient,
    private val queryProvider: QueryProvider,
    private val mapper: Mapper
) : PostsRepository {

    override fun getPosts(page: Int, limit: Int): Observable<List<Post>> {
        return apolloClient.rxQuery(queryProvider.getPostsQuery(page, limit))
            .map {
                val nonNullList = it.data?.posts?.data?.filterNotNull() ?: emptyList()
                mapper.mapPosts(nonNullList)
            }
    }
}