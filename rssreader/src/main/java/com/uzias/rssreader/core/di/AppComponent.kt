package com.uzias.rssreader.core.di

import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.database.di.DatabaseModule
import com.uzias.rssreader.core.networking.di.NetworkModule
import dagger.Component
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, NetworkModule::class, DatabaseModule::class))
@Singleton
interface AppComponent {

    fun getRetrofit() : Retrofit

    fun getRSSReaderApplication() : RSSReaderApplication

    fun getReactiveEntityStore() : KotlinReactiveEntityStore<Persistable>
}