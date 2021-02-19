package com.eva.postify

import android.app.Application
import com.eva.postify.data.di.dataModule
import org.koin.core.context.startKoin

class PostifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            dataModule
        }
    }
}