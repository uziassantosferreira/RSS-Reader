package com.uzias.rssreader.core.di

import com.uzias.rssreader.core.application.RSSReaderApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(val RSSReaderApplication: RSSReaderApplication) {

    @Provides @Singleton fun providesRSSReaderApplication() = RSSReaderApplication

}
