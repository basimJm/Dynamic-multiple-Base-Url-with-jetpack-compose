package com.blackhand.dynamic_urls_compose.data.model.users

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SupportRemoteModel(
    @Json(name = "text")
    var text: String? = null,
    @Json(name = "url")
    var url: String? = null,
)