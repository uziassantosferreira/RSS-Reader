package com.uzias.rssreader.feed.data.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.uzias.rssreader.core.domain.InvalidData
import com.uzias.rssreader.feed.data.repository.datasource.FeedDatasource
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class FeedRepositoryImplTest {

    private val feedApiDatasource : FeedDatasource = mock()
    private val ormDatasource : FeedDatasource = mock()
    private lateinit var repository: FeedRepository
    private val rss = Rss()

    @Before
    fun setUp() {
        repository = FeedRepositoryImpl(feedApiDatasource, ormDatasource)
        given(ormDatasource.getRss()).willReturn(Observable.just(rss))
        given(ormDatasource.getRssByUrl(InvalidData.UNINITIALIZED.getString()))
                .willReturn(Observable.just(rss))
        given(feedApiDatasource.getRssByUrl(InvalidData.UNINITIALIZED.getString()))
                .willReturn(Observable.just(rss))
        given(feedApiDatasource.save(rss))
                .willReturn(Observable.just(rss))
    }

    @Test
    fun must_call_ormdatasource_get_rss() {
        repository.getRss()
        verify(ormDatasource).getRss()
    }

    @Test
    fun must_call_get_rss_by_link() {
        repository.addRss(InvalidData.UNINITIALIZED.getString())
        verify(ormDatasource).getRssByUrl(InvalidData.UNINITIALIZED.getString())
        verify(feedApiDatasource).getRssByUrl(InvalidData.UNINITIALIZED.getString())
    }

    @Test
    fun must_call_orm_delete_rss() {
        repository.deleteRss(InvalidData.UNINITIALIZED.getString())
        verify(ormDatasource).deleteRss(InvalidData.UNINITIALIZED.getString())
    }

}