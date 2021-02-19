package com.eva.postify.data.mapper

import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import com.eva.postify.data.model.Post
import com.eva.postify.data.model.PostDetails

internal interface Mapper {
    fun mapPosts(posts: List<PostsQuery.Data1>): List<Post>
    fun mapDetails(details: PostDetailsQuery.Post): PostDetails
}