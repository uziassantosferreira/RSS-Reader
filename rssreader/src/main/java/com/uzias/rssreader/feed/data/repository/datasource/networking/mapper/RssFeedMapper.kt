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
                    var image = ""
                    var title = ""
                    var link = ""
                    var publishDate = ""
                    var description = ""
                    if (it.image != null){
                        image = it.image
                    }
                    if (it.title != null){
                        title = it.title
                    }
                    if (it.link != null){
                        link = it.link
                    }
                    if (it.publishDate != null){
                        publishDate = it.publishDate
                    }
                    if (it.description != null){
                        description = it.description
                    }
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
