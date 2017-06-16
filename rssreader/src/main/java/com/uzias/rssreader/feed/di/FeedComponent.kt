package com.uzias.rssreader.feed.di

import com.uzias.rssreader.feed.di.scope.FeedScope
import com.uzias.rssreader.core.di.AppComponent
import com.uzias.rssreader.feed.presentation.view.FeedActivity
import dagger.Component

@FeedScope
@Component(modules = arrayOf(FeedModule::class), dependencies = arrayOf(AppComponent::class))
interface FeedComponent {

    fun inject(feedActivity: FeedActivity)

}
