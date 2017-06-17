package com.uzias.rssreader.feed.presentation.mapper

import com.uzias.rssreader.feed.domain.model.Rss
import junit.framework.Assert.assertEquals
import org.junit.Test

class PresentationRssMapperTest {

    companion object {
        private val MOCK_URL = "URL"
    }

    @Test
    fun correctly_transform_domain_rss_to_presentation() {
        val list = mutableListOf(PresentationItemMapperTest.mockItem(),
                PresentationItemMapperTest.mockItem())
        val rss = Rss(MOCK_URL, list)
        val presentationRss = PresentationRssMapper.transformFrom(rss)
        assertEquals(rss.url, MOCK_URL)
        assertEquals(rss.items.size, list.size)
        presentationRss.items.forEach {
            PresentationItemMapperTest.assertTransformation(it, PresentationItemMapperTest.mockItem())
        }
    }

}