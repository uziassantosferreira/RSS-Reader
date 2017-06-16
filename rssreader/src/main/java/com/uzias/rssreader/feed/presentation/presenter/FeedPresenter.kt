package com.uzias.rssreader.feed.presentation.presenter

import com.uzias.rssreader.core.presentation.BasePresenter

interface FeedPresenter: BasePresenter {

    fun clickedButtonAdd()
    fun clickedButtonOkInputUrl(url: String)

}