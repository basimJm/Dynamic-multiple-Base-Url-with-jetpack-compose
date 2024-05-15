package com.blackhand.dynamic_urls_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blackhand.dynamic_urls_compose.ui.feature.chat.ChatScreen
import com.blackhand.dynamic_urls_compose.ui.feature.conversations.component.ConversationsScreen
import com.blackhand.dynamic_urls_compose.ui.feature.conversations.viewmodel.ConversationsViewModel
import com.blackhand.dynamic_urls_compose.ui.feature.movie.component.MovieScreen
import com.blackhand.dynamic_urls_compose.ui.feature.movie.viewmodel.MovieViewModel
import com.blackhand.dynamic_urls_compose.ui.feature.users.component.UsersScreen
import com.blackhand.dynamic_urls_compose.ui.feature.users.viewmodel.UsersViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: androidx.compose.ui.Modifier,
    conversationViewModel: ConversationsViewModel,
    usersViewModel: UsersViewModel,
    movieViewModel: MovieViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Users.route,
        modifier = modifier
    ) {
        composable(
            route = NavigationScreens.Users.route
        ) {
            UsersScreen(navController, usersViewModel)
        }
        composable(route = NavigationScreens.Movie.route) {
            MovieScreen(movieViewModel)
        }
        composable(route = NavigationScreens.Conversations.route) {
            ConversationsScreen(navController, conversationViewModel)
        }
        composable(route = NavigationScreens.Chat.route) {
            ChatScreen()
        }
    }
}


