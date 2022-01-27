package com.android.newsfeed.presentation.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.newsfeed.domain.usecase.GetFeedUseCase

class ViewModelFactory(
    private val app: Application,
    private val getFeedUseCase: GetFeedUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            app,
            getFeedUseCase
        ) as T
    }
}