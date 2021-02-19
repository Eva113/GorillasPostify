package com.eva.postify.data.service

import com.apollographql.apollo.api.toInput
import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import com.eva.postify.data.type.PageQueryOptions
import com.eva.postify.data.type.PaginateOptions

internal class QueryProviderImpl : QueryProvider {

    override fun getPostsQuery(page: Int, limit: Int): PostsQuery {
        return PostsQuery(
            PageQueryOptions(
                PaginateOptions(
                    page.toInput(),
                    limit.toInput()
                ).toInput()
            ).toInput()
        )
    }

    override fun getDetailsQuery(id: String): PostDetailsQuery {
        return PostDetailsQuery(id)
    }
}