package com.eva.postify.data.service

import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery

internal interface QueryProvider {
    fun getPostsQuery(page: Int, limit: Int): PostsQuery

    fun getDetailsQuery(id: String): PostDetailsQuery
}