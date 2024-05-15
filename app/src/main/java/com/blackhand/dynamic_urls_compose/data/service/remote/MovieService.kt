package com.blackhand.dynamic_urls_compose.data.service.remote

import com.blackhand.dynamic_urls_compose.data.model.movie.MovieRemoteResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("/3/movie/popular")
    suspend fun getMoviesList(): Response<MovieRemoteResponseModel>
}