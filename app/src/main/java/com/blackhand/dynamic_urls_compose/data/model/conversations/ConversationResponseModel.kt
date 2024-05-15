package com.blackhand.dynamic_urls_compose.data.model.conversations

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationResponseModel(
    @Json(name = "__v")
    var v: Int?=null,
    @Json(name = "_id")
    var id: String?=null,
    @Json(name = "lastMessage")
    var lastMessage: String?=null,
    @Json(name = "lastSenderName")
    var lastSenderName: String?=null,
    @Json(name = "messages")
    var messages: List<String>?=null,
    @Json(name = "users")
    var users: List<UserRemoteModel>?=null,
)