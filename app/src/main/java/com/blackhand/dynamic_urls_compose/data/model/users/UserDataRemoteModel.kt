package com.blackhand.dynamic_urls_compose.data.model.users

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataRemoteModel(
    @Json(name = "avatar")
    var avatar: String? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "first_name")
    var firstName: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "last_name")
    var lastName: String? = null,
)