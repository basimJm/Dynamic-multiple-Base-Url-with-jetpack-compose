package com.blackhand.dynamic_urls_compose.domain.usecase

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel
import com.blackhand.dynamic_urls_compose.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<ResponseState<List<MovieResultModel>?>> {
        return movieRepository.getMovieList().map { response ->
            when (response) {
                is ResponseState.Loading -> {
                    ResponseState.Loading()
                }

                is ResponseState.Error -> {
                    ResponseState.Error(response.message)
                }

                is ResponseState.Success -> {
                    ResponseState.Success(response.data?.sortedBy { data -> data.title })
                }
            }

        }
    }
}