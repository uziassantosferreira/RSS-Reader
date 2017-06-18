package com.uzias.rssreader.feed.domain.repository

import com.uzias.rssreader.feed.domain.model.Rss
import io.reactivex.Completable
import io.reactivex.Observable

interface FeedRepository {

    fun getRss() : Observable<Rss>

    fun addRss(url: String) : Observable<Rss>

    fun deleteRss(url: String) : Completable

}