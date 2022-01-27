package com.android.newsfeed.data.repository.dataSourceImpl

import com.android.newsfeed.data.api.FeedAPIService
import com.android.newsfeed.data.model.FeedModel
import com.android.newsfeed.data.repository.dataSource.FeedRemoteDataSource
import retrofit2.Response

class FeedRemoteDataSourceImpl(private val feedAPIService: FeedAPIService) : FeedRemoteDataSource {
    override suspend fun getFeeds(): Response<FeedModel> {
        return feedAPIService.getFeeds()
    }
}