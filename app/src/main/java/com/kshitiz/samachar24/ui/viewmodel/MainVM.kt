package com.kshitiz.samachar24.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kshitiz.samachar24.data.repository.NewsRepository
import com.kshitiz.samachar24.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(val repository: NewsRepository) : ViewModel() {
    private val _news = MutableStateFlow<UiState>(UiState.Loading)
    val news = _news.asStateFlow()

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        repository.getAllNewsFromDb().collect {
            _news.value = UiState.Success(it)
        }
    }


    fun refresh() = viewModelScope.launch {
        repository.refreshFeeds()
    }

}