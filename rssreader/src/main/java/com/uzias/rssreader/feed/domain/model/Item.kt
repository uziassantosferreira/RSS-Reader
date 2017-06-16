package com.uzias.rssreader.feed.domain.model

import com.uzias.rssreader.core.domain.InvalidData

data class Item(var title: String = InvalidData.UNINITIALIZED.getString(),
                var url: String = InvalidData.UNINITIALIZED.getString(),
                var description: String = InvalidData.UNINITIALIZED.getString(),
                var pubDate: String = InvalidData.UNINITIALIZED.getString(),
                var imageUrl: String = InvalidData.UNINITIALIZED.getString())