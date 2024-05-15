package com.blackhand.dynamic_urls_compose.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blackhand.dynamic_urls_compose.ui.feature.conversations.viewmodel.ConversationsViewModel
import com.blackhand.dynamic_urls_compose.ui.feature.movie.viewmodel.MovieViewModel
import com.blackhand.dynamic_urls_compose.ui.feature.users.viewmodel.UsersViewModel
import com.blackhand.dynamic_urls_compose.ui.navigation.MainNavGraph
import com.blackhand.dynamic_urls_compose.ui.navigation.NavigationScreens
import com.blackhand.dynamic_urls_compose.ui.theme.Purple80

val screens = listOf(
    NavigationScreens.Conversations,
    NavigationScreens.Users,
    NavigationScreens.Movie
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val conversationsViewModel = hiltViewModel<ConversationsViewModel>()
    val usersViewModel = hiltViewModel<UsersViewModel>()
    val moviesViewModel = hiltViewModel<MovieViewModel>()



    Scaffold(modifier = Modifier.fillMaxWidth(), bottomBar = { BottomBar(navHostController) }) {
        MainNavGraph(
            navController = navHostController,
            modifier = Modifier.padding(it), conversationsViewModel, usersViewModel, moviesViewModel
        )
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val navBarColor = remember { mutableStateOf(Color.Yellow) }
    val currentDestination = navBackStackEntry?.destination
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    HandleNavBarUi(navBackStackEntry, bottomBarState, navBarColor)

    AnimatedVisibility(
        visible = bottomBarState.value,
        exit = ExitTransition.None,
        enter = EnterTransition.None
    ) {
        NavigationBar(containerColor = navBarColor.value) {
            screens.forEach { screen ->
                AddItem(screen, currentDestination, navHostController)
            }
        }
    }
}

@Composable
private fun HandleNavBarUi(
    navBackStackEntry: NavBackStackEntry?,
    bottomBarState: MutableState<Boolean>,
    navBarColor: MutableState<Color>
) {
    when (navBackStackEntry?.destination?.route) {
        NavigationScreens.Chat.route -> bottomBarState.value = false
        NavigationScreens.Users.route -> navBarColor.value = Color.Yellow
        NavigationScreens.Conversations.route -> navBarColor.value = Color.Red
        NavigationScreens.Movie.route -> navBarColor.value = Purple80
        else -> bottomBarState.value = true
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationScreens,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                screen.selectedIcon
            } else screen.unSelectedIcon, contentDescription = null)
        })
}
