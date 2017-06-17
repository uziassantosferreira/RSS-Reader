package com.uzias.rssreader.feed.presentation.mapper

import com.uzias.rssreader.feed.domain.model.Item
import com.uzias.rssreader.feed.presentation.model.PresentationItem
import junit.framework.Assert.assertEquals
import org.junit.Test

class PresentationItemMapperTest {

    companion object {
        private val MOCK_TITLE = "TITLE"
        private val MOCK_URL = "URL"
        private val MOCK_DESCRIPTION = "DESCRIPTION"
        private val MOCK_PUB_DATE = "PUBDATE"
        private val MOCK_IMAGE_URL = "IMAGEURL"

        fun assertTransformation(presentationItem: PresentationItem, item: Item){
            assertEquals(presentationItem.title, item.title)
            assertEquals(presentationItem.url, item.url)
            assertEquals(presentationItem.description, item.description)
            assertEquals(presentationItem.pubDate, item.pubDate)
            assertEquals(presentationItem.imageUrl, item.imageUrl)
        }

        fun mockItem() = Item(title = MOCK_TITLE, url = MOCK_URL, description = MOCK_DESCRIPTION,
                    pubDate = MOCK_PUB_DATE, imageUrl = MOCK_IMAGE_URL)
    }

    @Test
    fun correctly_transform_domain_item_to_presentation() {
        val presentationItem = PresentationItemMapper.transformFrom(mockItem())
        assertTransformation(presentationItem, mockItem())
    }

}