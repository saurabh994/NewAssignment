package com.example.assignmentproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignmentproject.di.TestAppComponent
import com.example.assignmentproject.network.Urls
import com.example.assignmentproject.ui.base.viewmodel.factory.ActivityViewModelFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.File
import kotlin.test.AfterTest

abstract class BaseRepositoryTest {
    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var activityViewModelFactory: ActivityViewModelFactory

    lateinit var testAppComponent: TestAppComponent

    lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
        //this.configureDi()
    }

    @AfterTest
    open fun tearDown() {
        this.stopMockServer()
    }

    abstract fun isMockServerEnabled(): Boolean

    open fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockServer = MockWebServer()
            mockServer.start(8080)
            Urls.BASE_URL = mockServer.url("/").toString()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) =
        mockServer.enqueue(MockResponse().setResponseCode(responseCode).setBody(getJson(fileName)))


    open fun getJson(path: String): String {
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}