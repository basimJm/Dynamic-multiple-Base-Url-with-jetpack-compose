package com.blackhand.dynamic_urls_compose.domain.model.conversations

data class UserModel(
    var v: Int? = null,
    var id: String? = null,
    var authToken: String? = null,
    var conversations: List<String>? = null,
    var email: String? = null,
    var fcmToken: String? = null,
    var fullName: String? = null,
    var imageUrl: String? = null,
    var password: String? = null,
)