package com.uzias.rssreader.core.networking.di

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import me.toptas.rssconverter.RssConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named

@Module
class NetworkModule {

    companion object {

        private val API_URL : String = "http://rss.nytimes.com/"

    }

    @Provides
    @Named("baseUrl")
    fun providesBaseUrl(): String = API_URL

    @Provides
    fun providesRxJava2CallAdapter(): RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.create()

    @Provides
    fun providesRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                         @Named("baseUrl") baseUrl: String): Retrofit
            = Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(RssConverterFactory.create())
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(baseUrl)
            .build()

}