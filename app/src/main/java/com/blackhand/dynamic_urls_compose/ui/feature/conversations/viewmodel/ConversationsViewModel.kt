package com.blackhand.dynamic_urls_compose.ui.feature.conversations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.repo.ConversationsRepository
import com.blackhand.dynamic_urls_compose.domain.usecase.ConversationsUseCase
import com.blackhand.dynamic_urls_compose.ui.feature.conversations.state.ConversationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(private val conversationsUseCase: ConversationsUseCase) :
    ViewModel() {

    private val _conversationsState = MutableStateFlow(ConversationState())

    val conversationsState: StateFlow<ConversationState>
        get() = _conversationsState

    init {
        loadConversations()
    }

    private fun loadConversations() {
        conversationsUseCase().onEach {
            when (it) {
                is ResponseState.Loading ->
                    _conversationsState.value = ConversationState().copy(isLoading = true)

                is ResponseState.Success -> _conversationsState.value =
                    ConversationState().copy(conversations = it.data)

                is ResponseState.Error -> _conversationsState.value =
                    ConversationState().copy(errorMessage = it.message)
            }
        }.launchIn(viewModelScope)
    }
}