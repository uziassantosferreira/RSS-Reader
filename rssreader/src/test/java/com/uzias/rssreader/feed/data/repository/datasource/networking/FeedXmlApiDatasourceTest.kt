package com.uzias.rssreader.feed.data.repository.datasource.networking

import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.model.RssHelper.Companion.mockRss
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class FeedXmlApiDatasourceTest {

    private val feedXmlApi: FeedXmlApi = mock()

    private lateinit var datasource: FeedXmlApiDatasource

    @Before
    fun setUp() {
        datasource = FeedXmlApiDatasource(feedXmlApi)
    }

    @Test
    fun correctly_returns_empty_get_rss(){
        val testObserver = TestObserver.create<Rss>()
            datasource.getRss()
                    .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoValues()
    }

    @Test
    fun correctly_returns_empty_save(){
        val testObserver = TestObserver.create<Rss>()
        datasource.save(mockRss())
                .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoValues()
    }

}