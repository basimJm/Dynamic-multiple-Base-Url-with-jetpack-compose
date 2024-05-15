package com.blackhand.dynamic_urls_compose.domain.usecase

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel
import com.blackhand.dynamic_urls_compose.domain.repo.ConversationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ConversationsUseCase @Inject constructor(private val conversationsRepository: ConversationsRepository) {

    operator fun invoke(): Flow<ResponseState<List<ConversationModel>?>> {
        return conversationsRepository.getConversations()
            .map { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val sortedConversations = responseState.data?.sortedBy { it.id }
                        ResponseState.Success(sortedConversations)
                    }

                    is ResponseState.Loading -> ResponseState.Loading()
                    is ResponseState.Error -> responseState
                }
            }
    }
}