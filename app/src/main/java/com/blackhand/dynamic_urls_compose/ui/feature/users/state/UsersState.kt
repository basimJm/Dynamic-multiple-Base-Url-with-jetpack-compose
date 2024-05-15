package com.blackhand.dynamic_urls_compose.ui.feature.users.state

import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel

data class UsersState(
    var usersList: List<UserDataModel>? = null,
    var isLoading: Boolean? = null, var errorMessage: String? = null
)
