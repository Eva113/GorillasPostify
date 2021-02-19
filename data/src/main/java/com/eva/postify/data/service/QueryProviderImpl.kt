package com.eva.postify.data.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.rx3.rxQuery
import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import com.eva.postify.data.type.PageQueryOptions
import com.eva.postify.data.type.PaginateOptions
import io.reactivex.rxjava3.core.Observable

internal class QueryProviderImpl(private val appolloClient : ApolloClient) : QueryProvider {

    override fun getPostsQuery(page: Int, limit: Int): Observable<PostsQuery.Data> {
        val query =  PostsQuery(
            PageQueryOptions(
                PaginateOptions(
                    page.toInput(),
                    limit.toInput()
                ).toInput()
            ).toInput()
        )
        return appolloClient.rxQuery(query).map { it.data }
    }

    override fun getDetailsQuery(id: String): Observable<PostDetailsQuery.Data> {
        val query = PostDetailsQuery(id)
        return appolloClient.rxQuery(query).map { it.data }
    }
}