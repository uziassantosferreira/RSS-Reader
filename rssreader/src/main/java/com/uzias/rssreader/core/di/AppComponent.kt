package com.uzias.rssreader.core.di

import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.networking.di.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
@Singleton
interface AppComponent {

    fun getRetrofit() : Retrofit

    fun getRSSReaderApplication() : RSSReaderApplication
}