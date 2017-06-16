package com.uzias.rssreader.feed.presentation

import com.uzias.rssreader.feed.presentation.model.PresentationItem

interface ItemListener {
    fun clicked(presentationItem: PresentationItem)
}