package com.eva.postify.navigator

import android.app.Activity

interface Navigator {

    sealed class Destination {
        data class PostDetails(val postId: String) : Destination()

        object ListingPost : Destination()
    }

    fun navigateTo(activity: Activity, destination: Destination)

}