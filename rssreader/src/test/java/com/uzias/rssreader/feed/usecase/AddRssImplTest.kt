package com.uzias.rssreader.feed.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.core.domain.InvalidData
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

class AddRssImplTest {

    private lateinit var addRss: AddRss
    private var repository: FeedRepository = mock()
    private var rss: Rss = Rss()

    @Test
    fun successful_execute() {
        given(repository.addRss(InvalidData.UNINITIALIZED.getString()))
                .willReturn(Observable.just(rss))
        val testObserver = TestObserver<Rss>()

        addRss = AddRssImpl(repository)
        addRss = addRss.setUrl(InvalidData.UNINITIALIZED.getString())
        addRss.run()
                .subscribe(testObserver)

        testObserver.assertValue(rss)
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }

}