package com.uzias.rssreader.core.networking

import com.nhaarman.mockito_kotlin.mock
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.di.AppComponent
import com.uzias.rssreader.core.di.AppModule
import com.uzias.rssreader.core.di.DaggerAppComponent
import com.uzias.rssreader.core.networking.di.NetworkModule
import com.uzias.rssreader.core.util.fileFromResource
import org.junit.After
import org.junit.Before
import javax.inject.Inject
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class MockRestApi<T: Any> {

    private val application: RSSReaderApplication = mock()
    private lateinit var server: MockWebServer

    lateinit var file: String
    lateinit var testAppComponent: AppComponent
    lateinit var endpoint: String
    abstract val resource: String

    @Inject lateinit var restApi: T

    @Before
    open fun setUp() {
        setupReadFile()
        setupServer()
        injectDependencies()
    }

    private fun setupReadFile() {
        file = fileFromResource(resource, javaClass)
    }

    private fun setupServer() {
        server = MockWebServer()
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(file))
        server.start()
        endpoint = server.url("/").toString()
    }

    private fun injectDependencies() {
        testAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .networkModule(NetworkModule())
                .build()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}