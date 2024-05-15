package com.blackhand.dynamic_urls_compose.data.service.remote

import com.blackhand.dynamic_urls_compose.data.model.users.UsersResponseRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("/api/users")
    suspend fun getUsers(): Response<UsersResponseRemoteModel>
}