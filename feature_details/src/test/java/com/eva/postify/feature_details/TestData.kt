package com.eva.postify.feature_details

import com.eva.postify.data.model.PostDetails

internal object TestData {
    val details = PostDetails("name", "username", "post title", "post description")
    val error = Throwable()

}