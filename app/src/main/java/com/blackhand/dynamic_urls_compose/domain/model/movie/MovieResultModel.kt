package com.blackhand.dynamic_urls_compose.domain.model.movie

data class MovieResultModel(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: List<Int>? = null,
    var id: Int? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)