package com.eva.postify.data.mapper

import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import com.eva.postify.data.model.Post
import com.eva.postify.data.model.PostDetails

//mapper uses !! because normally these values shouldn't be null. If something goes wrong with network or apollo requests
//the null pointer exception will be handled by the rx chain and the error will be displayed to the user

internal class MapperImpl : Mapper {
    override fun mapPosts(posts: List<PostsQuery.Data1>): List<Post> {
        return posts.map {
            Post(it.id!!, it.title!!, it.body!!)
        }
    }

    override fun mapDetails(details: PostDetailsQuery.Post): PostDetails {
        return PostDetails(
            details.user!!.name!!,
            details.user.username!!,
            details.title!!,
            details.body!!
        )
    }
}