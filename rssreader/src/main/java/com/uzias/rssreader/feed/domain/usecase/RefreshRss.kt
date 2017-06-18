package com.uzias.rssreader.feed.domain.usecase

import com.uzias.rssreader.core.domain.InvalidData
import com.uzias.rssreader.core.domain.UseCase
import com.uzias.rssreader.feed.domain.model.Rss
import io.reactivex.Observable

interface RefreshRss : UseCase<Observable<Rss>> {
    fun setUrl(url: String = InvalidData.UNINITIALIZED.getString()) : RefreshRss
}
