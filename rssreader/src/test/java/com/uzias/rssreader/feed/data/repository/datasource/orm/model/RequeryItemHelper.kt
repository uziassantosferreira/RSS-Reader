package com.uzias.rssreader.feed.data.repository.datasource.orm.model

class RequeryItemHelper {

    companion object {

        private val MOCK_TITLE = "TITLE"
        private val MOCK_URL = "URL"
        private val MOCK_DESCRIPTION = "DESCRIPTION"
        private val MOCK_PUB_DATE = "PUBDATE"
        private val MOCK_IMAGE_URL = "IMAGEURL"

        fun mockRequeryItem(): RequeryItem {
            val requeryItem = RequeryItemEntity()
            with(requeryItem){
                title = MOCK_TITLE
                url = MOCK_URL
                description = MOCK_DESCRIPTION
                publishDate = MOCK_PUB_DATE
                image = MOCK_IMAGE_URL
            }
            return requeryItem
        }

    }

}
