package com.uzias.rssreader.feed.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class GetRssImplTest {

    private lateinit var getRss: GetRss
    private var repository: FeedRepository = mock()
    private var rss: Rss = Rss()

    @Test
    fun successful_execute() {
        given(repository.getRss())
                .willReturn(Observable.just(rss))
        val testObserver = TestObserver<Rss>()

        getRss = GetRssImpl(repository)
        getRss.run()
                .subscribe(testObserver)

        testObserver.assertValue(rss)
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

}