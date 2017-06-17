package com.uzias.rssreader.feed.data.repository.datasource.orm

import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.database.DatabaseModuleTest
import com.uzias.rssreader.core.di.AppModule
import com.uzias.rssreader.core.di.DaggerAppComponent
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItemEntity
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryRssEntity
import com.uzias.rssreader.feed.di.DaggerFeedComponentTest
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.model.RssHelper.Companion.mockRss
import io.reactivex.observers.TestObserver
import org.junit.Before
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import org.junit.After
import org.junit.Test
import javax.inject.Inject

class RequeryDatasourceImplTest {

    private var application = mock<RSSReaderApplication>()

    private lateinit var datasource: RequeryDatasourceImpl

    @Inject
    lateinit var data: KotlinReactiveEntityStore<Persistable>

    @Before
    fun setUp() {
        injectDependencies()
        datasource = RequeryDatasourceImpl(data)
        createRss()
    }

    private fun createRss() {
        val rss = mockRss()
        val requeryRss = RequeryRssEntity()
        requeryRss.url = rss.url
        data.insert(requeryRss).blockingGet()
        rss.items.forEach {
            val requeryItem =  RequeryItemEntity()
            requeryItem.title = it.title
            requeryItem.url = it.url
            requeryItem.image = it.imageUrl
            requeryItem.description = it.description
            requeryItem.publishDate = it.pubDate
            requeryItem.requeryItem = requeryRss
            data.insert(requeryItem).blockingGet()
        }
    }

    private fun injectDependencies() {
        val appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .databaseModule(DatabaseModuleTest())
                .build()

        val feedComponentTest = DaggerFeedComponentTest.builder()
                .appComponent(appComponent)
                .build()

        feedComponentTest.inject(this)
    }

    @After
    fun teardown() {
        data.close()
    }

    @Test
    fun successful_get_rss() {
        val testObserver = TestObserver.create<Rss>()
        datasource.getRss()
                .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertResult(mockRss())
        testObserver.assertNoErrors()
    }

    @Test
    fun successful_get_rss_by_url() {
        val testObserver = TestObserver.create<Rss>()
        val rss = mockRss()
        datasource.getRssByUrl(rss.url)
                .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertResult(rss)
        testObserver.assertNoErrors()
    }

    @Test
    fun successful_save_rss() {
        val rss = mockRss()
        val testObserver = TestObserver.create<Rss>()
        datasource.save(rss)
                .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertResult(rss)
        testObserver.assertNoErrors()
    }

}