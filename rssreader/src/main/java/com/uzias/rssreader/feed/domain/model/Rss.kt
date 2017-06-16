package com.uzias.rssreader.feed.domain.model

import com.uzias.rssreader.core.domain.InvalidData

data class Rss(var url: String = InvalidData.UNINITIALIZED.getString(),
               var items: MutableList<Item> = mutableListOf())