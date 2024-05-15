package com.blackhand.dynamic_urls_compose.domain.model.conversations

data class ConversationModel(
    var v: Int? = null,
    var id: String? = null,
    var lastMessage: String? = null,
    var lastSenderName: String? = null,
    var messages: List<String>? = null,
    var users: List<UserModel>? = null,
)