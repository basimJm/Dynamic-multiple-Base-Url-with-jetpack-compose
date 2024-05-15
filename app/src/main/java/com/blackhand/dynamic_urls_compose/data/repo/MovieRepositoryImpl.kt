package com.blackhand.dynamic_urls_compose.data.repo

import android.util.Log
import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.core.util.mapper.toMovieModel
import com.blackhand.dynamic_urls_compose.core.util.url.MovieUrl
import com.blackhand.dynamic_urls_compose.data.service.remote.MovieService
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel
import com.blackhand.dynamic_urls_compose.domain.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(@MovieUrl private val movieService: MovieService) :
    MovieRepository {
    override fun getMovieList(): Flow<ResponseState<List<MovieResultModel>?>> {
        return flow {
            emit(ResponseState.Loading())
            val movieResponse = movieService.getMoviesList()
            if (movieResponse.isSuccessful && movieResponse.body() != null) {
                emit(ResponseState.Success(movieResponse.body()?.toMovieModel()?.results))
            } else {
                emit(ResponseState.Error(movieResponse.message()))
            }
        }.flowOn(Dispatchers.IO).catch {
            emit(ResponseState.Error(it.message))
        }
            .flowOn(Dispatchers.IO)
    }
}