package com.blackhand.dynamic_urls_compose.data.model.users

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponseRemoteModel(
    @Json(name = "data")
    var data: List<UserDataRemoteModel>? = null,
    @Json(name = "page")
    var page: Int? = null,
    @Json(name = "per_page")
    var perPage: Int? = null,
    @Json(name = "support")
    var support: SupportRemoteModel? = null,
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "total_pages")
    var totalPages: Int
)