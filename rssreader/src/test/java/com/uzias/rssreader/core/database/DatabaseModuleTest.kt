package com.uzias.rssreader.core.database

import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.database.di.DatabaseModule
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.Models
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.*
import org.sqlite.SQLiteConfig
import org.sqlite.SQLiteDataSource

class DatabaseModuleTest : DatabaseModule() {

    override fun provideDatabase(database: DatabaseSource): KotlinReactiveEntityStore<Persistable> {
        val instance = KotlinEntityDataStore<Persistable>(providesConfiguration())
        return KotlinReactiveEntityStore(instance)
    }

    fun providesConfiguration(): Configuration {
        val model = Models.DEFAULT
        val dataSource = provideSQLiteDatabaseSource()
        val configuration = KotlinConfiguration(
                dataSource = dataSource,
                model = model,
                statementCacheSize = 0,
                useDefaultLogging = true)
        val tables = SchemaModifier(configuration)
        tables.dropTables()
        val mode = TableCreationMode.CREATE
        tables.createTables(mode)
        return configuration
    }
    fun provideSQLiteDatabaseSource() : SQLiteDataSource {
        val dataSource = SQLiteDataSource()
        dataSource.url = "jdbc:sqlite:/tmp/test.sqlite"
        val config = SQLiteConfig()
        config.setDateClass("TEXT")
        dataSource.config = config
        val statement = dataSource.connection.createStatement()
        statement.execute("PRAGMA foreign_keys = ON")
        return dataSource
    }
    override fun provideDatabaseSource(application: RSSReaderApplication, entityModel: EntityModel):
            DatabaseSource {
        return mock()
    }

}