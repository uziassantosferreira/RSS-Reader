package com.uzias.rssreader.feed.data.repository.datasource.networking.model

/*
import org.simpleframework.xml.*

@Namespace(prefix = "media", reference = "http://search.yahoo.com/mrss/")
@Root(name = "item", strict = false)
class FeedItem(
    @field:Element(name = "pubDate")
    var pubDate: String? = null,
    @field:Element(name = "title")
    var title: String? = null,

*/
/*    @field:Attribute(name = "url")
    @Path("Service/Pieces")
    var url: String? = null,*//*


    @field:ElementList(entry = "link", inline = true, required = false)
    var link: MutableList<String>? = null,

    @field:ElementList(entry = "description", inline = true, required = false)
    var description: MutableList<String>? = null

)*/
