package com.uzias.rssreader.feed.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.core.domain.InvalidData
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class RefreshRssImplTest {

    private lateinit var refreshRss: RefreshRss
    private var repository: FeedRepository = mock()
    private var rss: Rss = Rss()

    @Test
    fun successful_execute() {
        given(repository.addRss(InvalidData.UNINITIALIZED.getString()))
                .willReturn(Observable.just(rss))
        given(repository.deleteRss(InvalidData.UNINITIALIZED.getString()))
                .willReturn(Completable.complete())
        val testObserver = TestObserver<Rss>()

        refreshRss = RefreshRssImpl(repository)

        refreshRss.setUrl(InvalidData.UNINITIALIZED.getString())
                .run()
                .subscribe(testObserver)
        testObserver.assertValue(rss)
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

}