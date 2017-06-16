package com.uzias.rssreader.feed.domain.usecase

import com.uzias.rssreader.core.domain.UseCase
import com.uzias.rssreader.feed.domain.model.Rss
import io.reactivex.Observable

interface GetRss : UseCase<Observable<Rss>>