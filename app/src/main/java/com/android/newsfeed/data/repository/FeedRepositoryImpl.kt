package com.android.newsfeed.data.repository

import com.android.newsfeed.data.model.FeedModel
import com.android.newsfeed.data.repository.dataSource.FeedRemoteDataSource
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.domain.repository.FeedRepository
import retrofit2.Response

class FeedRepositoryImpl(private val feedRemoteDataSource: FeedRemoteDataSource) : FeedRepository {
    override suspend fun getFeeds(): Resource<FeedModel> {
        return responseToResource(feedRemoteDataSource.getFeeds())
    }

    private fun responseToResource(response: Response<FeedModel>): Resource<FeedModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}