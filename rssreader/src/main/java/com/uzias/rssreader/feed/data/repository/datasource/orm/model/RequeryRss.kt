package com.uzias.rssreader.feed.data.repository.datasource.orm.model

import android.os.Parcelable
import io.requery.*

@Entity
interface RequeryRss : Persistable, Parcelable {
    @get:Key
    @get:Generated
    val id: Int

    var url: String

    @get:OneToMany(mappedBy = "requeryItem")
    val requeryItems: MutableSet<RequeryItem>
}
