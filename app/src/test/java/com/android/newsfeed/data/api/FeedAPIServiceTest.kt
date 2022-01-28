package com.android.newsfeed.data.api

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedAPIServiceTest {
    private lateinit var service: FeedAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FeedAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getFeeds_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("feedresponse.json")
            val responseBody = service.getFeeds().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/facts.json")
        }
    }

    @Test
    fun getFeeds_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("feedresponse.json")
            val responseBody = service.getFeeds().body()
            val feedList = responseBody!!.rows
            val feed = feedList[0]
            assertThat(feed.title).isEqualTo("Beavers")
            assertThat(feed.description).isEqualTo("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony")
            assertThat(feed.imageHref).isEqualTo("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}