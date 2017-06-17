package com.uzias.rssreader.feed.di

import com.uzias.rssreader.core.di.AppComponent
import com.uzias.rssreader.core.networking.MockRestApi
import com.uzias.rssreader.feed.data.repository.datasource.networking.FeedXmlApi
import com.uzias.rssreader.feed.data.repository.datasource.orm.RequeryDatasourceImplTest
import com.uzias.rssreader.feed.di.scope.FeedScopeTest
import dagger.Component

@FeedScopeTest
@Component(modules = arrayOf(FeedModule::class), dependencies = arrayOf(AppComponent::class))
interface FeedComponentTest {

    fun inject(requeryDatasourceImplTest: RequeryDatasourceImplTest)

    fun inject(mockRestApi: MockRestApi<FeedXmlApi>)

}
