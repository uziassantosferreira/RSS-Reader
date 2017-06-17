package com.uzias.rssreader.feed.data.repository.datasource.orm.mapper

import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItem
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItemHelper.Companion.mockRequeryItem
import com.uzias.rssreader.feed.domain.model.Item
import junit.framework.Assert.assertEquals
import org.junit.Test

class RequeryItemMapperTest {

    companion object {
        fun assertTransformation(requeryItems: MutableList<RequeryItem>, items: MutableList<Item>){
            requeryItems.forEachIndexed { index, requeryItem -> 
                assertTransformation(requeryItem, items[index])
            }
            assertEquals(requeryItems.size, items.size)

        }
        fun assertTransformation(requeryItem: RequeryItem, item: Item){
            assertEquals(requeryItem.title, item.title)
            assertEquals(requeryItem.url, item.url)
            assertEquals(requeryItem.description, item.description)
            assertEquals(requeryItem.publishDate, item.pubDate)
            assertEquals(requeryItem.image, item.imageUrl)
        }
    }

    @Test
    fun correctly_transform_requery_item_to_domain(){
        val requeryItem = mockRequeryItem()
        val item = RequeryItemMapper.transformFrom(requeryItem)
        assertTransformation(requeryItem, item)
    }

    @Test
    fun correctly_transform_list_requery_item_to_list_domain(){
        val requeryItems = mutableListOf(mockRequeryItem(), mockRequeryItem()).toMutableSet()
        val items = RequeryItemMapper.transformFrom(requeryItems)
        assertTransformation(requeryItems.toMutableList(), items)
    }

}