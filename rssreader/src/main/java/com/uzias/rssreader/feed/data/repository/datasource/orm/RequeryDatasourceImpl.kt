package com.uzias.rssreader.feed.data.repository.datasource.orm

import com.uzias.rssreader.feed.data.repository.datasource.FeedDatasource
import com.uzias.rssreader.feed.data.repository.datasource.orm.mapper.RequeryRssMapper
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItem
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItemEntity
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryRss
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryRssEntity
import com.uzias.rssreader.feed.domain.model.Rss
import io.reactivex.Completable
import io.reactivex.Observable
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore

class RequeryDatasourceImpl(val reactiveEntityStore: KotlinReactiveEntityStore<Persistable>)
    : FeedDatasource {

    override fun save(rss: Rss): Observable<Rss> {
        val requeryRss = RequeryRssEntity()
        requeryRss.url = rss.url
        reactiveEntityStore.insert(requeryRss).blockingGet()
        rss.items.forEach {
            val requeryItem =  RequeryItemEntity()
            requeryItem.title = it.title
            requeryItem.url = it.url
            requeryItem.image = it.imageUrl
            requeryItem.description = it.description
            requeryItem.publishDate = it.pubDate
            requeryItem.requeryItem = requeryRss
            reactiveEntityStore.insert(requeryItem).blockingGet()
        }
        return Observable.just(rss)
    }

    override fun getRssByUrl(url: String): Observable<Rss>
            = reactiveEntityStore.select(RequeryRss::class)
               .where(RequeryRssEntity.URL.eq(url))
               .get()
               .observable()
               .map{RequeryRssMapper.transformFrom(it)}

    override fun getRss(): Observable<Rss> = reactiveEntityStore.select(RequeryRssEntity::class)
            .get()
            .observable()
            .map{RequeryRssMapper.transformFrom(it)}

    override fun deleteRss(url: String): Completable {
        reactiveEntityStore.delete(RequeryRss::class)
                .where(RequeryRssEntity.URL.eq(url))
                .get().value()
        return Completable.complete()
    }

}