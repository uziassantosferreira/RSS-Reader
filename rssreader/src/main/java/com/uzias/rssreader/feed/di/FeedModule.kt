package com.uzias.rssreader.feed.di

import com.uzias.rssreader.feed.data.repository.FeedRepositoryImpl
import com.uzias.rssreader.feed.data.repository.datasource.FeedDatasource
import com.uzias.rssreader.feed.data.repository.datasource.networking.FeedXmlApi
import com.uzias.rssreader.feed.data.repository.datasource.networking.FeedXmlApiDatasource
import com.uzias.rssreader.feed.data.repository.datasource.orm.RequeryDatasourceImpl
import com.uzias.rssreader.feed.domain.repository.FeedRepository
import com.uzias.rssreader.feed.domain.usecase.AddRss
import com.uzias.rssreader.feed.domain.usecase.AddRssImpl
import com.uzias.rssreader.feed.domain.usecase.GetRss
import com.uzias.rssreader.feed.domain.usecase.GetRssImpl
import com.uzias.rssreader.feed.presentation.presenter.FeedPresenter
import com.uzias.rssreader.feed.presentation.presenter.FeedPresenterImpl
import dagger.Module
import dagger.Provides
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import retrofit2.Retrofit
import javax.inject.Named

@Module class FeedModule {

    @Provides fun providesFeedPresenter(addRss: AddRss, getRss: GetRss)
            : FeedPresenter = FeedPresenterImpl(addRss, getRss)


    @Provides fun providesAddRss(feedRepository: FeedRepository) : AddRss
            = AddRssImpl(feedRepository)

    @Provides fun providesGetRss(feedRepository: FeedRepository) : GetRss
            = GetRssImpl(feedRepository)

    @Provides fun providesFeedRepository(@Named("feedApiDatasource")
                                         feedApiDatasource: FeedDatasource,
                                         @Named("feedOrmDatasource")
                                         feedOrmDatasource: FeedDatasource)
            : FeedRepository
            = FeedRepositoryImpl(feedApiDatasource, feedOrmDatasource)

    @Named("feedApiDatasource")
    @Provides fun providesFeedApiDatasource(feedXmlApi: FeedXmlApi) : FeedDatasource
            = FeedXmlApiDatasource(feedXmlApi)

    @Named("feedOrmDatasource")
    @Provides fun providesFeedOrmDatasource(reactiveEntityStore:
                                                KotlinReactiveEntityStore<Persistable>)
            : FeedDatasource
            = RequeryDatasourceImpl(reactiveEntityStore)

    @Provides fun providesFeedXmlApi(retrofit: Retrofit) : FeedXmlApi
            = retrofit.create(FeedXmlApi::class.java)
}
