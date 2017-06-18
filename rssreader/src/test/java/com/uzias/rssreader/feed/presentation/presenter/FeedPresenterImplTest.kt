package com.uzias.rssreader.feed.presentation.presenter

import com.nhaarman.mockito_kotlin.*
import com.uzias.rssreader.core.util.Rx2TestBase
import com.uzias.rssreader.core.domain.InvalidData
import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.domain.usecase.AddRss
import com.uzias.rssreader.feed.domain.usecase.GetRss
import com.uzias.rssreader.feed.domain.usecase.RefreshRss
import com.uzias.rssreader.feed.presentation.model.PresentationRss
import com.uzias.rssreader.feed.presentation.view.FeedView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class FeedPresenterImplTest : Rx2TestBase() {

    private var addRss: AddRss = mock()
    private var getRss: GetRss = mock()
    private var refreshRss: RefreshRss = mock()
    private var view: FeedView = mock()
    private var rss: Rss = Rss()
    private val presentationRss = PresentationRss("", mutableListOf())
    private lateinit var presenter: FeedPresenter

    @Before
    fun setUp(){
        given(addRss.setUrl(InvalidData.UNINITIALIZED.getString())).willReturn(addRss)
        given(addRss.run()).willReturn(Observable.just(rss))
        given(getRss.run()).willReturn(Observable.just(rss))
        given(refreshRss.setUrl(InvalidData.UNINITIALIZED.getString())).willReturn(refreshRss)
        given(refreshRss.run()).willReturn(Observable.just(rss))
        presenter = FeedPresenterImpl(addRss, getRss, refreshRss)
    }

    @Test
    fun open_dialog_input_url() {
        presenter.attachTo(view)
        presenter.clickedButtonAdd()
        verify(view).openDialogInputUrl()
    }


    @Test
    fun add_rss() {
        presenter.attachTo(view)
        verify(view).addRss(any())
    }

    @Test
    fun input_url() {
        presenter.attachTo(view)
        presenter.clickedButtonOkInputUrl(InvalidData.UNINITIALIZED.getString())
        verify(view, times(2)).addRss(any())
        verify(view).showLoading()
        verify(view).dismissLoading()
    }

    @Test
    fun refresh_rss() {
        presenter.attachTo(view)
        presenter.setPresentationSelected(presentationRss)
        presenter.refreshFeedActioned()
        verify(view, times(2)).addRss(any())
        verify(view).showLoading()
        verify(view).dismissLoading()
        verify(view).removeRss(presentationRss)
        view.setSelectedRss(any())
    }

}