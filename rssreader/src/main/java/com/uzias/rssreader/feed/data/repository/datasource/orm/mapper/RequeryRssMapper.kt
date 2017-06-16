package com.uzias.rssreader.feed.data.repository.datasource.orm.mapper

import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryRss
import com.uzias.rssreader.feed.domain.model.Rss

class RequeryRssMapper {

    companion object {
        fun transformFrom(requeryRss: RequeryRss): Rss
                = Rss(url = requeryRss.url,
                items = RequeryItemMapper.transformFrom(requeryRss.requeryItem))
    }

}