package com.uzias.rssreader.feed.domain.usecase

import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable

class RefreshRssImpl(val repository: FeedRepository) : RefreshRss {

    private lateinit var url: String

    override fun run(): Observable<Rss> = repository.deleteRss(url).to { repository.addRss(url) }

    override fun setUrl(url: String): RefreshRss {
        this.url = url
        return this
    }

}
