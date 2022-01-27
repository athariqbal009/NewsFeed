package com.android.newsfeed.presentation.di

import com.android.newsfeed.domain.repository.FeedRepository
import com.android.newsfeed.domain.usecase.GetFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetUserUseCase(feedRepository: FeedRepository): GetFeedUseCase {
        return GetFeedUseCase(feedRepository)
    }
}