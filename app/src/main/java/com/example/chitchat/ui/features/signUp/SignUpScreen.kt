package com.example.chitchat.ui.features.signUp


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chitchat.R
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.lightGray
import com.example.chitchat.ui.theme.lightGrayishBlue
import com.example.chitchat.ui.theme.shapes
import com.example.chitchat.ui.theme.textBlack
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.transparent
import com.example.chitchat.ui.theme.white
import com.example.chitchat.utils.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import dev.burnoo.cokoin.viewmodel.getViewModel

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {
    val navigation = getNavController()
    val viewModel = getViewModel<SignUpViewModel>()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MainCardView(viewModel) {}

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = textGray
                )
            )
            TextButton(
                onClick = {
                    navigation.navigate(MyScreens.SignInScreen.root)
                }) {
                Text(text = "Sign in")
            }
        }
        Spacer(modifier = Modifier.height(90.dp))

    }

}

@Composable
fun MainCardView(viewModel: SignUpViewModel, signUpEvent: () -> Unit) {
    val name = viewModel.name.observeAsState("")
    val email = viewModel.email.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val confirmPassword = viewModel.confirmPassword.observeAsState("")
    val context = LocalContext.current

    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp)
                .height(444.dp),
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
                    text = "Sign Up",
                    Modifier.padding(top = 20.dp),
                    style = TextStyle(
                        color = textBlack,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )

                Text(
                    text = "Follow the easy steps",
                    Modifier.padding(top = 8.dp),
                    style = TextStyle(
                        color = textGray,
                        fontSize = 15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                MainTextField(
                    edtValue = name.value,
                    icon = R.drawable.ic_person,
                    hint = "Name",
                    isPassword = false

                ) { viewModel.name.value = it }

                MainTextField(
                    edtValue = email.value,
                    icon = R.drawable.ic_email,
                    hint = "Email",
                    isPassword = false
                ) { viewModel.email.value = it }

                MainTextField(
                    edtValue = password.value,
                    icon = R.drawable.ic_password,
                    hint = "Password",
                    isPassword = true

                ) { viewModel.password.value = it }

                MainTextField(
                    edtValue = confirmPassword.value,
                    icon = R.drawable.ic_password,
                    hint = "Confirm Password",
                    isPassword = true
                ) { viewModel.confirmPassword.value = it }

                Button(
                    onClick = { signUpEvent },
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 28.dp, end = 28.dp),
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = blue)
                ) {
                    Text(
                        text = "Create Account",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    edtValue: String, icon: Int, hint: String, isPassword: Boolean, onValueChanges: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }

    TextField(
        label = { Text(text = hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, start = 28.dp, end = 28.dp),
        shape = shapes.medium,
        leadingIcon = { Icon(painterResource(icon), contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = lightGrayishBlue,
            focusedIndicatorColor = transparent, unfocusedIndicatorColor = transparent,
            errorIndicatorColor = transparent, disabledIndicatorColor = transparent,
            focusedLabelColor = textBlack, unfocusedLabelColor = textGray,
            focusedLeadingIconColor = textBlack, unfocusedLeadingIconColor = textGray,
            focusedTrailingIconColor = textBlack, unfocusedTrailingIconColor = textGray
        ),
        visualTransformation = if (passwordVisible.value || !isPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (!isPassword) KeyboardType.Email else KeyboardType.Password
        ),
        trailingIcon = {
            if (isPassword){
                val image = if (passwordVisible.value) painterResource(id = R.drawable.ic_visible)
                else painterResource(id = R.drawable.ic_invisible)

                Icon(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        passwordVisible.value = !passwordVisible.value
                    }
                )
            }
        }
    )
}