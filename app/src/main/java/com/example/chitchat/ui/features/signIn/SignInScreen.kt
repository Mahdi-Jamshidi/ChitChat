package com.example.chitchat.ui.features.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chitchat.R
import com.example.chitchat.ui.features.signUp.MainTextField
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.lightGray
import com.example.chitchat.ui.theme.shapes
import com.example.chitchat.ui.theme.textBlack
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.white
import com.example.chitchat.utils.MyScreens
import dev.burnoo.cokoin.navigation.getNavController

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}

@Composable
fun SignInScreen() {
    val navigation = getNavController()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MainCardView() {}

        Box(
            Modifier.padding(top = 10.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account yet?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = textGray
                    )
                )
                TextButton(
                    onClick = {
                        navigation.navigate(MyScreens.SignUpScreen.root)
                    }) {
                    Text(text = "Sign up")
                }
            }
            TextButton(
                modifier = Modifier.padding(top = 30.dp),
                onClick = {
                    navigation.navigate(MyScreens.SignInScreen.root)
                }) {
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = blue
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(90.dp))

    }
}

@Composable
fun MainCardView(signInEvent: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfig by remember { mutableStateOf("") }

    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp)
                .height(326.dp),
            colors = CardDefaults.cardColors(
                containerColor = lightGray
            )
        ) {}
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = shapes.large,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign In",
                    Modifier.padding(top = 20.dp),
                    style = TextStyle(
                        color = textBlack,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )

                Text(
                    text = "Login to your account",
                    Modifier.padding(top = 8.dp),
                    style = TextStyle(
                        color = textGray,
                        fontSize = 15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                MainTextField(
                    edtValue = email,
                    icon = R.drawable.ic_email,
                    hint = "Email",
                    isPassword = false
                ) { email = it }

                MainTextField(
                    edtValue = password,
                    icon = R.drawable.ic_password,
                    hint = "Password",
                    isPassword = true

                ) { password = it }

                Button(
                    onClick = { signInEvent },
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 28.dp, end = 28.dp),
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = blue)
                ) {
                    Text(
                        text = "Sign In",
                        style = TextStyle(
                            fontSize = 15.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.heightIn(24.dp))
            }
        }
    }

}