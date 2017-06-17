package com.uzias.rssreader.feed.data.repository.datasource.orm.mapper

import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItem
import com.uzias.rssreader.feed.domain.model.Item

class RequeryItemMapper {

    companion object {
        fun transformFrom(requeryItem: RequeryItem): Item
                = Item(title = requeryItem.title, url = requeryItem.url,
                description = requeryItem.description, pubDate = requeryItem.publishDate,
                imageUrl =  requeryItem.image)

        fun transformFrom(requeryItems: MutableSet<RequeryItem>): MutableList<Item>{
            val items = mutableListOf<Item>()
            requeryItems.forEach {
                items.add(transformFrom(it))
            }
            return items
        }
    }

}