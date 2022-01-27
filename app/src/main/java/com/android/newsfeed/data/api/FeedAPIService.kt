package com.android.newsfeed.data.api

import com.android.newsfeed.data.model.FeedModel
import retrofit2.Response
import retrofit2.http.GET

interface FeedAPIService {
    @GET("facts.json")
    suspend fun getFeeds(): Response<FeedModel>
}