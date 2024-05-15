package com.blackhand.dynamic_urls_compose.domain.repo

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<ResponseState<List<MovieResultModel>?>>
}