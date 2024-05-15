package com.blackhand.dynamic_urls_compose.domain.model.users

data class UserModel(
    var data: List<UserDataModel>? = null,
    var page: Int? = null,
    var perPage: Int? = null,
    var support: SupportModel? = null,
    var total: Int? = null,
    var totalPages: Int
)
