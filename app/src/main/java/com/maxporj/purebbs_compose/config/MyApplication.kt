package com.maxporj.purebbs_compose.config

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.maxporj.purebbs_compose.net.HttpService

//import com.facebook.stetho.Stetho

class MyApplication: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        Config.application = this

//        Stetho.initializeWithDefaults(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient(HttpService.okHttpClient)
            .build()
    }
}