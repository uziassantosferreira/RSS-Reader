package com.uzias.rssreader.feed.data.repository.datasource.networking.mapper

import com.uzias.rssreader.feed.domain.model.Item
import com.uzias.rssreader.feed.domain.model.Rss
import me.toptas.rssconverter.RssFeed

class RssFeedMapper {

    companion object {
        fun transformFrom(rssFeed: RssFeed, url: String): Rss {
            val items = mutableListOf<Item>()
            rssFeed.items?.let {
                it.forEach {
                    val image = if (it.image != null) it.image else ""
                    val title = if (it.title != null) it.title else ""
                    val link = if (it.link != null) it.link else ""
                    val publishDate = if (it.publishDate != null) it.publishDate else ""
                    val description = if (it.description != null) it.description else ""
                    val item = Item(title = title, url = link, imageUrl = image,
                            pubDate =  publishDate, description =  description)
                    items.add(item)
                }
            }
            val rss = Rss(url = url, items = items)
            return rss
        }

    }

}
