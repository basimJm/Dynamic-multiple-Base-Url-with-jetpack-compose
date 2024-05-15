package com.blackhand.dynamic_urls_compose.domain.model.movie

data class MovieModel(
    var page: Int? = null,
    var results: List<MovieResultModel>? = null,
    var totalPages: Int? = null,
    var totalResults: Int? = null,
)