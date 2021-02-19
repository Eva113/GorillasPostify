package com.eva.postify.feature_list

import com.eva.postify.data.model.Post


internal object TestData {
    val listOfPosts = mutableListOf(Post("1", "Title", "Body"))
    val error = Throwable()
}