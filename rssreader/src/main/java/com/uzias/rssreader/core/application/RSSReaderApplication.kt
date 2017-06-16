package com.uzias.rssreader.core.application

import android.app.Application
import com.uzias.rssreader.core.di.AppComponent
import com.uzias.rssreader.core.di.AppModule
import com.uzias.rssreader.core.di.DaggerAppComponent
import com.uzias.rssreader.core.networking.di.NetworkModule

class RSSReaderApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}