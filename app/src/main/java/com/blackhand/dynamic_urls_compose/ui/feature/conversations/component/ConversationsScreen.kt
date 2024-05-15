package com.blackhand.dynamic_urls_compose.ui.feature.conversations.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.blackhand.dynamic_urls_compose.domain.model.conversations.ConversationModel
import com.blackhand.dynamic_urls_compose.ui.common_ui.ErrorMessage
import com.blackhand.dynamic_urls_compose.ui.common_ui.LoadingProgressBar
import com.blackhand.dynamic_urls_compose.ui.common_ui.RemoteImage
import com.blackhand.dynamic_urls_compose.ui.feature.conversations.viewmodel.ConversationsViewModel
import com.blackhand.dynamic_urls_compose.ui.theme.Typography

@Composable
fun ConversationsScreen(navController: NavController, viewModel: ConversationsViewModel) {
    val state = viewModel.conversationsState.collectAsStateWithLifecycle()
    if (state.value.isLoading == true) {
        LoadingProgressBar()
    } else if (state.value.errorMessage != null) {
        ErrorMessage(error = state.value.errorMessage)

    } else if (state.value.conversations != null) {
        ConversationsSectionsList(state.value.conversations)
    }
}

@Composable
fun ConversationsSectionsList(conversations: List<ConversationModel>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        conversations?.let { conv ->
            items(conv) { items ->
                ConversationCard(items)
            }
        }

    }
}

@Composable
fun ConversationCard(conversationModel: ConversationModel?) {

    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .heightIn(70.dp)
                    .width(30.dp)
            ) {
                RemoteImage(
                    data = conversationModel?.users?.get(0)?.imageUrl.toString(),
                    modifier = Modifier.clip(
                        RoundedCornerShape(0.dp)
                    )
                )
            }
            Column(modifier = Modifier.padding(start = 3.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = conversationModel?.lastSenderName.toString(),
                        modifier = Modifier.padding(5.dp),
                        color = Color.Black,
                        style = Typography.titleMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = conversationModel?.lastMessage.toString(),
                        modifier = Modifier.padding(5.dp),
                        color = Color.Gray,
                        style = Typography.bodyMedium
                    )
                    Icon(
                        Icons.Outlined.Email, contentDescription = "", modifier = Modifier

                            .padding(5.dp), tint = Color.Red
                    )
                }
            }

        }
    }
    Divider(modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 3.dp))

}

