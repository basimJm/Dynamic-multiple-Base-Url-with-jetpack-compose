package com.blackhand.dynamic_urls_compose.data.model.conversations

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRemoteModel(
    @Json(name = "__v")
    var v: Int?=null,
    @Json(name = "_id")
    var id: String?=null,
    @Json(name = "authToken")
    var authToken: String?=null,
    @Json(name = "conversations")
    var conversations: List<String>?=null,
    @Json(name = "email")
    var email: String?=null,
    @Json(name = "fcmToken")
    var fcmToken: String?=null,
    @Json(name = "fullName")
    var fullName: String?=null,
    @Json(name = "imageUrl")
    var imageUrl: String?=null,
    @Json(name = "password")
    var password: String?=null,
)