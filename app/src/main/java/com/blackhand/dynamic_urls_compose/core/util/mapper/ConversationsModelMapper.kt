package com.blackhand.dynamic_urls_compose.core.util.mapper

import com.blackhand.dynamic_urls_compose.data.model.conversations.ConversationResponseModel
import com.blackhand.dynamic_urls_compose.data.model.conversations.ConversationsSectionRemoteModel
import com.blackhand.dynamic_urls_compose.data.model.conversations.UserRemoteModel
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationsSectionModel
import com.blackhand.dynamic_urls_compose.domain.model.conversations.UserModel

fun ConversationsSectionRemoteModel.toConversationsSectionModel(): ConversationsSectionModel {
    return ConversationsSectionModel(
        conversation = conversation?.map { it.toConversationModel() }
    )
}

fun ConversationResponseModel.toConversationModel(): ConversationModel {
    return ConversationModel(
        v = v,
        id = id,
        lastMessage = lastMessage,
        lastSenderName = lastSenderName,
        messages = messages,
        users = users?.map { it.toUserModel() }
    )
}

fun UserRemoteModel.toUserModel(): UserModel {
    return UserModel(
        v = v,
        id = id,
        authToken = authToken,
        conversations = conversations,
        email = email,
        fcmToken = fcmToken,
        fullName = fullName,
        imageUrl = imageUrl,
        password = password,
    )
}