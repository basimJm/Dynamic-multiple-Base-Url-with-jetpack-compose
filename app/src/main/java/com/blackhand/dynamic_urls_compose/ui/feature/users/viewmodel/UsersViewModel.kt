package com.blackhand.dynamic_urls_compose.ui.feature.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.usecase.UsersUseCase
import com.blackhand.dynamic_urls_compose.ui.feature.users.state.UsersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersUseCase: UsersUseCase) : ViewModel() {

    private var _userState = MutableStateFlow(UsersState())
    val userState: StateFlow<UsersState>
        get() = _userState

    init {
        getUsersList()
    }

     fun getUsersList() {
        usersUseCase.invoke().onEach { response ->
            when (response) {
                is ResponseState.Loading -> {
                    _userState.value = UsersState().copy(isLoading = true)
                }

                is ResponseState.Success -> {
                    _userState.value = UsersState().copy(usersList = response.data)
                }

                is ResponseState.Error -> {
                    _userState.value = UsersState().copy(errorMessage = response.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}

