package com.example.chitchat.ui.features.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.chitchat.ui.features.signIn.SignInScreen
import com.example.chitchat.ui.theme.backgroundMain

@Preview(showBackground = true)
@Composable
fun FriendsScreenPreview() {
    FriendsScreen()
}

@Composable
fun FriendsScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(backgroundMain),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FriendsScreen",
            style = TextStyle(fontSize = 45.sp )
        )
    }
}