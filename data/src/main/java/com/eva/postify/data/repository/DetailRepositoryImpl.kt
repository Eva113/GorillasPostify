package com.eva.postify.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.rxQuery
import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.model.PostDetails
import com.eva.postify.data.service.QueryProvider
import io.reactivex.rxjava3.core.Observable

internal class DetailRepositoryImpl(
    private val apolloClient: ApolloClient,
    private val queryProvider: QueryProvider,
    private val mapper: Mapper
) : DetailRepository {
    override fun getPostDetails(id: String): Observable<PostDetails> {
        return apolloClient.rxQuery(queryProvider.getDetailsQuery(id))
            .map {
                mapper.mapDetails(it.data?.post!!)
            }

    }
}