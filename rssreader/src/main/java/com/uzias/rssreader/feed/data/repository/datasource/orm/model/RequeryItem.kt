package com.uzias.rssreader.feed.data.repository.datasource.orm.model

import android.os.Parcelable
import io.requery.*

@Entity
interface RequeryItem : Persistable, Parcelable {
    @get:Key
    @get:Generated
    val id: Int

    var title: String

    var url: String

    var image: String

    var publishDate: String

    var description: String

    @get:ManyToOne
    var requeryItem: RequeryRss

}