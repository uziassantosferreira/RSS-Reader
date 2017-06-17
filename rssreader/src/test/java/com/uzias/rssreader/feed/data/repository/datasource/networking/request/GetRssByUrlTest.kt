package com.uzias.rssreader.feed.data.repository.datasource.networking.request

import com.uzias.rssreader.core.networking.MockRestApi
import com.uzias.rssreader.feed.data.repository.datasource.networking.FeedXmlApi
import com.uzias.rssreader.feed.di.DaggerFeedComponentTest
import io.reactivex.observers.TestObserver
import me.toptas.rssconverter.RssFeed
import org.junit.Test

class GetRssByUrlTest : MockRestApi<FeedXmlApi>() {

    override val resource: String = "restapi/rss.xml"

    override fun setUp() {
        super.setUp()
        val feedComponentTest = DaggerFeedComponentTest.builder()
                .appComponent(testAppComponent)
                .build()

        feedComponentTest.inject(this)
    }

    @Test
    fun check_get_rss_by_url_response_success() {
        val testObserver = TestObserver<RssFeed>()

        restApi.getRss(endpoint)
                .subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}