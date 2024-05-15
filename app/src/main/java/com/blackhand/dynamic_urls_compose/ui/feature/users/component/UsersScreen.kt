package com.blackhand.dynamic_urls_compose.ui.feature.users.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.blackhand.dynamic_urls_compose.R
import com.blackhand.dynamic_urls_compose.domain.model.users.UserDataModel
import com.blackhand.dynamic_urls_compose.ui.common_ui.ErrorMessage
import com.blackhand.dynamic_urls_compose.ui.common_ui.LoadingProgressBar
import com.blackhand.dynamic_urls_compose.ui.common_ui.RemoteImage
import com.blackhand.dynamic_urls_compose.ui.feature.users.state.UsersState
import com.blackhand.dynamic_urls_compose.ui.feature.users.viewmodel.UsersViewModel


@Composable
fun UsersScreen(navController: NavController, usersViewModel: UsersViewModel) {
    LaunchedEffect(key1 = true) {
        usersViewModel.getUsersList()
    }
    val usersDataList = usersViewModel.userState.collectAsStateWithLifecycle()
    HandleUiState(usersDataList = usersDataList.value)
}

@Composable
fun HandleUiState(usersDataList: UsersState) {
    if (usersDataList.isLoading == true) {
        LoadingProgressBar()
    } else if (usersDataList.errorMessage != null) {
        ErrorMessage(error = usersDataList.errorMessage)
    } else {
        UsersRowSection(usersList = usersDataList.usersList)
    }
}


@Composable
fun UsersRowSection(usersList: List<UserDataModel>?) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        usersList?.let { users ->
            items(users) { item ->
                UserCard(item)
            }
        }
    }
}

@Composable
fun UserCard(item: UserDataModel) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Yellow)


    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(20.dp))
                .padding(5.dp)
        ) {
            RemoteImage(item.avatar.toString(), modifier = Modifier.clip(CircleShape))

        }
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = item.firstName.toString(),
                modifier = Modifier
                    .padding(5.dp),
                style = androidx.compose.material3.Typography().titleLarge
            )
            Text(
                text = item.email.toString(),
                modifier = Modifier
                    .padding(top = 5.dp),
                style = androidx.compose.material3.Typography().bodyLarge
            )
        }
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "", modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(5.dp)
        )

    }
}

