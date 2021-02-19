package com.eva.postify

import android.app.Application
import com.eva.postify.data.di.dataModule
import com.eva.postify.feature_details.di.detailsModule
import com.eva.postify.feature_list.di.postsModule
import org.koin.android.java.KoinAndroidApplication
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class PostifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val koin: KoinApplication =
            KoinAndroidApplication.create(this).modules(dataModule, postsModule, detailsModule)
        startKoin(koinApplication = koin)
    }
}