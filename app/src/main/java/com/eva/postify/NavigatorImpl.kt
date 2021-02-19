package com.eva.postify

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.eva.postify.navigator.Navigator

class NavigatorImpl : Navigator {

    override fun navigateTo(activity: Activity, destination: Navigator.Destination) {
        val navController = activity.findNavController(R.id.nav_host_fragment)
        when (destination) {
            is Navigator.Destination.PostDetails -> {
                val bundle = bundleOf("postId" to destination.postId)
                navController.navigate(R.id.action_open_details, bundle)
            }
            else -> {
            }
        }
    }
}