package com.example.chitchat.ui.features.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.chitchat.ui.theme.MainAppTheme
import com.example.chitchat.ui.theme.textBlack

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Main Screen!",
            style = TextStyle(
                color = textBlack,
                fontSize = 50.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainAppTheme {
        MainScreen()
    }
}