package com.eva.postify.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetails(
    val name: String,
    val username: String,
    val postTitle: String,
    val postDescription: String
) : Parcelable