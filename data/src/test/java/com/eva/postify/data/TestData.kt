package com.eva.postify

import com.eva.postify.data.PostDetailsQuery
import com.eva.postify.data.PostsQuery
import com.eva.postify.data.model.Post
import com.eva.postify.data.model.PostDetails

internal object TestData {
    val listOfPosts = listOf(Post("1", "Title", "Body"))
    val listOfPostModels = listOf(PostsQuery.Data1("Post", "1", "Title", "Body"))
    val postsRepsonseData = PostsQuery.Data(PostsQuery.Posts(data = listOfPostModels))

    val postDetails = PostDetails("name", "username", "Title", "Body")
    val user = PostDetailsQuery.User("User", "name", "username")
    val postDetailsModel = PostDetailsQuery.Post("Post", "1", "Title", "Body", user)
    val postDetailResponseData = PostDetailsQuery.Data(postDetailsModel)
}