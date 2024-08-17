package com.android.newsfeed.presentation.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.newsfeed.data.model.FeedModel
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.domain.usecase.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val app: Application,
    private val getFeedUseCase: GetFeedUseCase
): AndroidViewModel(app) {
    private val _uiState = MutableLiveData<Resource<FeedModel>>()
    val uiState: LiveData<Resource<FeedModel>> get() = _uiState

    fun getFeeds() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.postValue(Resource.Loading())
        try {
            val feeds = getFeedUseCase.execute()
            _uiState.postValue(feeds)
        } catch (e:Exception) {
            _uiState.postValue(Resource.Error(e.message.toString()))
        }
    }
}