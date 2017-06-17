package com.uzias.rssreader.feed.presentation.mapper

import com.uzias.rssreader.feed.domain.model.Item
import com.uzias.rssreader.feed.domain.model.ItemHelper.Companion.mockItem
import com.uzias.rssreader.feed.presentation.model.PresentationItem
import junit.framework.Assert.assertEquals
import org.junit.Test

class PresentationItemMapperTest {

    companion object {

        fun assertTransformation(presentationItem: PresentationItem, item: Item){
            assertEquals(presentationItem.title, item.title)
            assertEquals(presentationItem.url, item.url)
            assertEquals(presentationItem.description, item.description)
            assertEquals(presentationItem.pubDate, item.pubDate)
            assertEquals(presentationItem.imageUrl, item.imageUrl)
        }

    }

    @Test
    fun correctly_transform_domain_item_to_presentation() {
        val presentationItem = PresentationItemMapper.transformFrom(mockItem())
        assertTransformation(presentationItem, mockItem())
    }

}