package com.blackhand.dynamic_urls_compose.domain.repo

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
     fun getAllUsers(): Flow<ResponseState<List<UserDataModel>?>>
}