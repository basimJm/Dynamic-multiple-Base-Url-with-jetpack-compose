package com.blackhand.dynamic_urls_compose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreens(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    data object Movie : NavigationScreens("Movie", "Movie", Icons.Default.Home, Icons.Outlined.Home)
    data object Users :
        NavigationScreens("Users", "Users", Icons.Default.Person, Icons.Outlined.Person)

    data object Conversations : NavigationScreens(
        "Conversations",
        "Conversations",
        Icons.Default.Email,
        Icons.Outlined.Email
    )
    data object Chat : NavigationScreens(
        "Chat",
        "Chat",
        Icons.Default.Face,
        Icons.Outlined.Face
    )
}