package com.uzias.rssreader.feed.domain.usecase

import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable

class AddRssImpl(val repository: FeedRepository) : AddRss {

    private lateinit var url: String

    override fun setUrl(url: String): AddRss {
        this.url = url
        return this
    }

    override fun run(): Observable<Rss> = repository.addRss(url)

}