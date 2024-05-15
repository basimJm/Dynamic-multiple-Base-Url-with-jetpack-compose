package com.blackhand.dynamic_urls_compose.data.service.remote

import com.blackhand.dynamic_urls_compose.data.model.conversations.ConversationsSectionRemoteModel
import retrofit2.http.GET

interface ConversationsService {

    @GET("/api/v1/message/all-conversations")
    suspend fun getConversations(): retrofit2.Response<ConversationsSectionRemoteModel?>
}