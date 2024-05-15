package com.blackhand.dynamic_urls_compose.domain.usecase

import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel
import com.blackhand.dynamic_urls_compose.domain.repo.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    operator fun invoke(): Flow<ResponseState<List<UserDataModel>?>> {
        return usersRepository.getAllUsers()
            .map { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        val sortedConversations = responseState.data?.sortedBy { it.firstName }
                        ResponseState.Success(sortedConversations)
                    }

                    is ResponseState.Loading -> ResponseState.Loading()
                    is ResponseState.Error -> responseState
                }
            }
    }

}