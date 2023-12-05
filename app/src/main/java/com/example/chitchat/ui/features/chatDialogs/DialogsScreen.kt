@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.chitchat.ui.features.chatDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chitchat.R
import com.example.chitchat.model.data.DialogsResponse
import com.example.chitchat.ui.theme.backgroundMain
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.lightGrayishBlue
import com.example.chitchat.ui.theme.shapes
import com.example.chitchat.ui.theme.textBlack
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.textGrayLight
import com.example.chitchat.ui.theme.transparent
import com.example.chitchat.ui.theme.white
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel

@Preview(showBackground = true)
@Composable
fun DialogsScreenPreview() {
}

@Composable
fun DialogsScreen() {
    val navController = getNavController()
    val viewModel = getViewModel<DialogsViewModel>()
    val searchValue by viewModel.searchValue.observeAsState("")
    viewModel.getDialogs()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundMain)
            .padding(top = 24.dp, start = 20.dp, end = 20.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.chats),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            )

            Spacer(modifier = Modifier.height(24.dp))
            SearchTextField(value = searchValue) {
                viewModel.searchValue.value = it
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

//        LazyColumn(contentPadding = PaddingValues(top = 16.dp)) {
        items(viewModel.dialogs) {
            ChatDialog(it)
        }
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = shapes.large,
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(R.string.searchHint),
                style = TextStyle(color = textGray, fontSize = 16.sp)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = textGray,
                modifier = Modifier.size(20.dp)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = transparent, disabledIndicatorColor = transparent,
            errorIndicatorColor = transparent, unfocusedIndicatorColor = transparent,
            containerColor = lightGrayishBlue,
        ),
    )
}

@Composable
fun ChatDialog(dialog: DialogsResponse.Data.Chat) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shapes.large)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = white),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 24.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            AsyncImage(
                model = dialog.avatar_address,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Row(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        text = dialog.user_name,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = textBlack
                        ),
                        maxLines = 1,

                        )
                    Text(
                        text = "12:45 PM",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = textGrayLight
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(0.8f),
                        text = dialog.title,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = textGray
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 22.sp
                    )
                    UnReadMessageCounter(4)
                }
            }
        }
    }
}

@Composable
fun UnReadMessageCounter(counter: Int) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$counter",
            style = TextStyle(
                fontSize = 12.sp,
                color = white,
            )
        )
    }
}