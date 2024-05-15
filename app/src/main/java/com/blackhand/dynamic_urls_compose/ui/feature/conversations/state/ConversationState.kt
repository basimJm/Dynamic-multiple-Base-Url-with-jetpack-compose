package com.blackhand.dynamic_urls_compose.ui.feature.conversations.state

import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel

data class ConversationState(
    var conversations: List<ConversationModel>? = null,
    var isLoading: Boolean? = false,
    var errorMessage: String? = null
)
