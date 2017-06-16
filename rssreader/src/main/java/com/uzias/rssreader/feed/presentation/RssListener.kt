package com.uzias.rssreader.feed.presentation

import com.uzias.rssreader.feed.presentation.model.PresentationRss

interface RssListener {
    fun clicked(presentationRss: PresentationRss)
}