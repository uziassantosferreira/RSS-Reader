package com.uzias.rssreader.feed.domain.model

class ItemHelper {

    companion object {

        private val MOCK_TITLE = "TITLE"
        private val MOCK_URL = "URL"
        private val MOCK_DESCRIPTION = "DESCRIPTION"
        private val MOCK_PUB_DATE = "PUBDATE"
        private val MOCK_IMAGE_URL = "IMAGEURL"

        fun mockItem() = Item(title = MOCK_TITLE, url = MOCK_URL, description = MOCK_DESCRIPTION,
                pubDate = MOCK_PUB_DATE, imageUrl = MOCK_IMAGE_URL)

    }

}
