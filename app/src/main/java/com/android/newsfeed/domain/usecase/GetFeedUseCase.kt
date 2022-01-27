package com.android.newsfeed.domain.usecase

import com.android.newsfeed.data.model.FeedModel
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.domain.repository.FeedRepository

class GetFeedUseCase(private val feedRepository: FeedRepository) {
    suspend fun execute(): Resource<FeedModel> {
        return feedRepository.getFeeds()
    }
}