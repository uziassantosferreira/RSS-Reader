package com.uzias.rssreader.feed.data.repository

import com.uzias.rssreader.feed.data.repository.datasource.FeedDatasource
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable

class FeedRepositoryImpl(val feedApiDatasource: FeedDatasource, val ormDatasource: FeedDatasource)
    : FeedRepository {

    override fun addRss(url: String): Observable<Rss> = ormDatasource.getRssByUrl(url)
                .switchIfEmpty(feedApiDatasource.getRssByUrl(url).flatMap{ormDatasource.save(it)})

    override fun getRss(): Observable<Rss> = ormDatasource.getRss()

}