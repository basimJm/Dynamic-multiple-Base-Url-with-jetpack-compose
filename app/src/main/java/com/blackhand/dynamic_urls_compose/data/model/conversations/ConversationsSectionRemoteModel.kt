package com.blackhand.dynamic_urls_compose.data.model.conversations

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationsSectionRemoteModel(
    @Json(name = "conversation")
    val conversation: List<ConversationResponseModel>? = null
)