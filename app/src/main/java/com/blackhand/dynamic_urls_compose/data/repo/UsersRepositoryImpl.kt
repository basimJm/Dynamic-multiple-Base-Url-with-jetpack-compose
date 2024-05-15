package com.blackhand.dynamic_urls_compose.data.repo

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.data.service.remote.UsersService
import com.blackhand.dynamic_urls_compose.core.util.mapper.toUserDataModel
import com.blackhand.dynamic_urls_compose.core.util.url.UsersUrl
import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel
import com.blackhand.dynamic_urls_compose.domain.repo.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(@UsersUrl private val userService: UsersService) :
    UsersRepository {
    override  fun getAllUsers(): Flow<ResponseState<List<UserDataModel>?>> {
        return flow {
            emit(ResponseState.Loading())
            val userData = userService.getUsers()
            if (userData.isSuccessful && userData.body() != null) {
                emit(ResponseState.Success(userData.body()?.data?.map { it.toUserDataModel() }))
            } else {
                emit(ResponseState.Error(userData.message()))
            }
        }.flowOn(Dispatchers.IO).catch {
            emit(ResponseState.Error(it.message))
        }.flowOn(Dispatchers.IO)
    }
}