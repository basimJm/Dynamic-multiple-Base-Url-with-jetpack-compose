package com.blackhand.dynamic_urls_compose.core.util.mapper

import com.blackhand.dynamic_urls_compose.data.model.movie.MovieRemoteResponseModel
import com.blackhand.dynamic_urls_compose.data.model.movie.MovieResultResponseModel
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieModel
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel

fun MovieRemoteResponseModel.toMovieModel(): MovieModel {
    return MovieModel(
        page = page,
        results = results?.map { it.toMovieResultModel() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}

fun MovieResultResponseModel.toMovieResultModel(): MovieResultModel {
    return MovieResultModel(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = getMoviePoster(posterPath),
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}

fun getMoviePoster(posterUrl: String?): String =
    "https://image.tmdb.org/t/p/w500${posterUrl}"


