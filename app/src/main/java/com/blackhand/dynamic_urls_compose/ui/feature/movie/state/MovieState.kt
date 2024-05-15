package com.blackhand.dynamic_urls_compose.ui.feature.movie.state

import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel

data class MovieState(
    var moviesList: List<MovieResultModel>? = null,
    var isLoading: Boolean? = null, var errorMessage: String? = null
)
