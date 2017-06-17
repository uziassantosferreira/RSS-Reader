package com.uzias.rssreader.feed.data.repository.datasource.orm.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryItemHelper.Companion.mockRequeryItem
import com.uzias.rssreader.feed.data.repository.datasource.orm.model.RequeryRss
import junit.framework.Assert
import org.junit.Test

class RequeryRssMapperTest {

    companion object {
        private val MOCK_URL = "URL"
    }

    @Test
    fun correctly_transform_requery_rss_to_domain() {
        val requeryRss = mock<RequeryRss>{
            on{ url}.doReturn(MOCK_URL)
            on{ requeryItems }.doReturn( mutableSetOf(mockRequeryItem(), mockRequeryItem()))
        }

        val rss = RequeryRssMapper.transformFrom(requeryRss)
        Assert.assertEquals(rss.url, MOCK_URL)
        RequeryItemMapperTest.assertTransformation(requeryRss.requeryItems.toMutableList(), rss.items)
    }

}