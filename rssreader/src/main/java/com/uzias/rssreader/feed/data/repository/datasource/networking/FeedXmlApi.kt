package com.uzias.rssreader.feed.data.repository.datasource.networking

import io.reactivex.Observable
import me.toptas.rssconverter.RssFeed
import retrofit2.http.GET
import retrofit2.http.Url

interface FeedXmlApi {

    @GET
    fun getRss(@Url url: String): Observable<RssFeed>

}