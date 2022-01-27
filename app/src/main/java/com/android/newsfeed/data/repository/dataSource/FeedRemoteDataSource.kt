package com.android.newsfeed.data.repository.dataSource

import com.android.newsfeed.data.model.FeedModel
import retrofit2.Response

interface FeedRemoteDataSource {
    suspend fun getFeeds(): Response<FeedModel>
}