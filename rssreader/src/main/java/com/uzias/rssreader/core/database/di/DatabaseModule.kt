package com.uzias.rssreader.core.database.di

import com.uzias.rssreader.BuildConfig
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.Models
import dagger.Module
import dagger.Provides
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.*
import javax.inject.Singleton

@Module
@Singleton
open class DatabaseModule {

    @Provides
    open fun provideDatabase(database: DatabaseSource): KotlinReactiveEntityStore<Persistable>
            = KotlinReactiveEntityStore(KotlinEntityDataStore(database.configuration))

    @Provides
    fun provideModels(): EntityModel = Models.DEFAULT

    @Provides
    open fun provideDatabaseSource(application: RSSReaderApplication,
                                       entityModel: EntityModel): DatabaseSource {
        val source = DatabaseSource(application, entityModel, 1)
        if (BuildConfig.DEBUG) {
            source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        }
        return source
    }

}