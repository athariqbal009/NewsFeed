package com.android.newsfeed.domain.repository

import com.android.newsfeed.data.model.FeedModel
import com.android.newsfeed.data.util.Resource

interface FeedRepository {
    suspend fun getFeeds(): Resource<FeedModel>
}