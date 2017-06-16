package com.uzias.rssreader.feed.presentation.view

import com.uzias.rssreader.core.presentation.BaseView
import com.uzias.rssreader.feed.presentation.model.PresentationRss

interface FeedView : BaseView {

    fun openDialogInputUrl()

    fun addRss(presentationRss: PresentationRss)

}