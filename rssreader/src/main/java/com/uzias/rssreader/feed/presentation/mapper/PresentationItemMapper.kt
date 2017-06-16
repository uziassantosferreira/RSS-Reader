package com.uzias.rssreader.feed.presentation.mapper

import com.uzias.rssreader.feed.domain.model.Item
import com.uzias.rssreader.feed.presentation.model.PresentationItem

class PresentationItemMapper {

    companion object {
        fun transformFrom(item: Item): PresentationItem
            = PresentationItem(title = item.title, url = item.url, imageUrl = item.imageUrl,
                    pubDate = item.pubDate, description = item.description)
    }

}
