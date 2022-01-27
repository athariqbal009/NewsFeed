package com.android.newsfeed.presentation.di

import android.app.Application
import com.android.newsfeed.domain.usecase.GetFeedUseCase
import com.android.newsfeed.presentation.vm.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideViewModelFactory(
        application: Application,
        getFeedUseCase: GetFeedUseCase
    ): ViewModelFactory {
        return ViewModelFactory(
            application,
            getFeedUseCase
        )
    }

}