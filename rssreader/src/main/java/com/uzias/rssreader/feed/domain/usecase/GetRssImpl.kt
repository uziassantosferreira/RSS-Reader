package com.uzias.rssreader.feed.domain.usecase

import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable

class GetRssImpl(val repository: FeedRepository) : GetRss {

    override fun run(): Observable<Rss> = repository.getRss()

}