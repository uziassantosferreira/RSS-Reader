package com.uzias.rssreader.feed.data.repository.datasource

import com.uzias.rssreader.feed.domain.model.Rss
import io.reactivex.Observable

interface FeedDatasource {
    fun getRss(): Observable<Rss>
    fun getRssByUrl(url: String): Observable<Rss>
    fun save(rss: Rss) : Observable<Rss>
}