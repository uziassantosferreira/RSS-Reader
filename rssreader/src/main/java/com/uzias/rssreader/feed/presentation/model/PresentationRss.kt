package com.uzias.rssreader.feed.presentation.model

data class PresentationRss(val url: String,
                           val items: MutableList<PresentationItem>)