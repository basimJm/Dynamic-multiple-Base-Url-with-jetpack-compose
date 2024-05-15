package com.blackhand.dynamic_urls_compose.domain.repo

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel

interface ConversationsRepository {
    fun getConversations(): kotlinx.coroutines.flow.Flow<ResponseState<List<ConversationModel>?>>
}