package com.eva.postify.data.repository

import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.model.PostDetails
import com.eva.postify.data.service.QueryProvider
import io.reactivex.rxjava3.core.Observable

internal class DetailRepositoryImpl(
    private val queryProvider: QueryProvider,
    private val mapper: Mapper
) : DetailRepository {

    override fun getPostDetails(id: String): Observable<PostDetails> {
        return queryProvider.getDetailsQuery(id)
            .map {
                mapper.mapDetails(it.post!!)
            }
    }
}