package com.uzias.rssreader.feed.domain.model

import com.uzias.rssreader.feed.domain.model.ItemHelper.Companion.mockItem

class RssHelper {

    companion object {

        private val MOCK_URL = "URL"

        fun mockRss() = Rss(url = MOCK_URL, items = mutableListOf(mockItem(), mockItem()))

    }

}
