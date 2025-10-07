package com.kshitiz.samachar24.ui.state

import com.kshitiz.samachar24.data.local.entity.NewsItem

sealed interface UiState {
    object Loading : UiState
    data class Success(val data: List<NewsItem>) : UiState
    data class Error(val message: String) : UiState
}