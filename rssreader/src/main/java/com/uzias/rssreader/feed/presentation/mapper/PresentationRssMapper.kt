
package com.uzias.rssreader.feed.presentation.mapper

import com.uzias.rssreader.feed.domain.model.Rss
import com.uzias.rssreader.feed.presentation.model.PresentationItem
import com.uzias.rssreader.feed.presentation.model.PresentationRss

class PresentationRssMapper {

    companion object {
        fun transformFrom(rss: Rss): PresentationRss {
            val items = mutableListOf<PresentationItem>()
            rss.items.forEach {
                items.add(PresentationItemMapper.transformFrom(it))
            }
            return PresentationRss(url = rss.url, items = items)
        }

    }

}
