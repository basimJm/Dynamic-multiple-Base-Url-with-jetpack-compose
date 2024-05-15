package com.blackhand.dynamic_urls_compose.core.util.mapper

import com.blackhand.dynamic_urls_compose.data.model.users.SupportRemoteModel
import com.blackhand.dynamic_urls_compose.data.model.users.UserDataRemoteModel
import com.blackhand.dynamic_urls_compose.data.model.users.UsersResponseRemoteModel
import com.blackhand.dynamic_urls_compose.domain.model.users.SupportModel
import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel
import com.blackhand.dynamic_urls_compose.domain.model.users.UserModel

fun UsersResponseRemoteModel.toUserModel(): UserModel {
    return UserModel(
        data = data?.map { it.toUserDataModel() },
        page = page,
        perPage = perPage,
        support = support?.toSupportModel(),
        total = total,
        totalPages = totalPages,
    )
}

fun UserDataRemoteModel.toUserDataModel(): UserDataModel {
    return UserDataModel(
        avatar = avatar,
        email = email,
        firstName = firstName,
        id = id,
        lastName = lastName,
    )
}

fun SupportRemoteModel.toSupportModel(): SupportModel {
    return SupportModel(
        text = text,
        url = url,
    )
}