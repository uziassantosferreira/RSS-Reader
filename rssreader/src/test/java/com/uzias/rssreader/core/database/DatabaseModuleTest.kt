package com.uzias.rssreader.core.database

import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.database.di.DatabaseModule
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.Models
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.KotlinConfiguration
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.SchemaModifier
import io.requery.sql.TableCreationMode
import org.sqlite.SQLiteConfig
import org.sqlite.SQLiteDataSource

class DatabaseModuleTest : DatabaseModule() {

    override fun provideDatabase(database: DatabaseSource): KotlinReactiveEntityStore<Persistable> {
        val model = Models.DEFAULT
        val dataSource = SQLiteDataSource()
        dataSource.url = "jdbc:sqlite:/tmp/test.sqlite"
        val config = SQLiteConfig()
        config.setDateClass("TEXT")
        dataSource.config = config
        val statement = dataSource.connection.createStatement()
        statement.execute("PRAGMA foreign_keys = ON");
        val configuration = KotlinConfiguration(
                dataSource = dataSource,
                model = model,
                statementCacheSize = 0,
                useDefaultLogging = true)

        val instance = KotlinEntityDataStore<Persistable>(configuration)
        val tables = SchemaModifier(configuration)
        tables.dropTables()
        val mode = TableCreationMode.CREATE
        tables.createTables(mode)

        return KotlinReactiveEntityStore(instance)
    }

    override fun provideDatabaseSource(application: RSSReaderApplication, entityModel: EntityModel):
            DatabaseSource {
        return mock()
    }

}