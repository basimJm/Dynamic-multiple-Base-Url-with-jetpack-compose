package com.blackhand.dynamic_urls_compose.data.repo

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.data.service.remote.ConversationsService
import com.blackhand.dynamic_urls_compose.core.util.url.ConversationsUrl
import com.blackhand.dynamic_urls_compose.core.util.mapper.toConversationsSectionModel
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel
import com.blackhand.dynamic_urls_compose.domain.repo.ConversationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConversationsRepositoryImpl @Inject constructor(
    @ConversationsUrl
    private val conversationsService: ConversationsService
) :
    ConversationsRepository {
    override fun getConversations(): kotlinx.coroutines.flow.Flow<ResponseState<List<ConversationModel>?>> {
        return flow {
            emit(ResponseState.Loading())
            if (conversationsService.getConversations().isSuccessful && conversationsService.getConversations()
                    .body() != null
            ) {
                emit(
                    ResponseState.Success(
                        conversationsService.getConversations().body()
                            ?.toConversationsSectionModel()?.conversation
                    )
                )
            } else {
                emit(
                    ResponseState.Error(
                        conversationsService.getConversations().errorBody().toString()
                    )
                )
            }
        }.flowOn(Dispatchers.IO).catch {
            emit(ResponseState.Error(it.message))
        }
    }
}