package com.uzias.rssreader.feed.presentation.presenter

import com.uzias.rssreader.core.presentation.BasePresenter
import com.uzias.rssreader.feed.presentation.model.PresentationRss

interface FeedPresenter: BasePresenter {

    fun clickedButtonAdd()
    fun clickedButtonOkInputUrl(url: String)
    fun refreshFeedActioned()
    fun setPresentationSelected(presentationRss: PresentationRss)

}