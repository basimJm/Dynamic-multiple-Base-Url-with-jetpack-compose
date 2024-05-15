package com.blackhand.dynamic_urls_compose.data.model.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieRemoteResponseModel(
    @Json(name = "page")
    var page: Int? = null,
    @Json(name = "results")
    var results: List<MovieResultResponseModel>? = null,
    @Json(name = "total_pages")
    var totalPages: Int? = null,
    @Json(name = "total_results")
    var totalResults: Int? = null,
)