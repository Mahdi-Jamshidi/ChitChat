package com.example.chitchat.ui.features.signIn

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chitchat.R
import com.example.chitchat.ui.features.signUp.MainTextField
import com.example.chitchat.ui.features.signUp.checkTextField
import com.example.chitchat.ui.features.signUp.getErrorText
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.lightGray
import com.example.chitchat.ui.theme.lightRed
import com.example.chitchat.ui.theme.red
import com.example.chitchat.ui.theme.shapes
import com.example.chitchat.ui.theme.textBlack
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.white
import com.example.chitchat.utils.NUMBER_ERROR
import com.example.chitchat.utils.NUMBER_VALID_ERROR
import com.example.chitchat.utils.MyScreens
import com.example.chitchat.utils.NOT_ERROR
import com.example.chitchat.utils.PASSWORD_ERROR
import com.example.chitchat.utils.PASSWORD_VALID_ERROR
import com.example.chitchat.utils.VALUE_SUCCESS
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import ir.dunijet.dunibazaar.util.NetworkChecker

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}

@Composable
fun SignInScreen() {
    val navigation = getNavController()
    val viewModel = getViewModel<SignInViewModel>()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MainCardView(viewModel) {
            viewModel.signInUser {
                viewModel.playLoadingAnim.value = false
                if (it == VALUE_SUCCESS) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    navigation.navigate(MyScreens.MainScreen.root) {
                        popUpTo(MyScreens.SignInScreen.root) {
                            inclusive = true
                        }
                    }
                } else {
                    viewModel.loggingMessage.value = it
                }
            }
        }

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
fun MainCardView(viewModel: SignInViewModel, signInEvent: () -> Unit) {
    val mobile = viewModel.mobile.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val errors = viewModel.errors.observeAsState(ArrayList())
    val loggingField = viewModel.loggingMessage.observeAsState("")
    val playLoadingAnim = viewModel.playLoadingAnim.observeAsState(false)
    val context = LocalContext.current

    Box {
        // main card shadow
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
                if (loggingField.value != "") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .padding(top = 14.dp, start = 28.dp, end = 28.dp)
                            .background(color = lightRed, shape = shapes.medium),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = loggingField.value,
                            modifier = Modifier
                                .background(color = lightRed),
                            style = TextStyle(
                                color = red,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                }
                val phoneNumError =
                    if (errors.value.contains(NUMBER_ERROR)) NUMBER_ERROR else if (errors.value.contains(
                            NUMBER_VALID_ERROR
                        )
                    ) NUMBER_VALID_ERROR else NOT_ERROR
                MainTextField(
                    edtValue = mobile.value,
                    icon = R.drawable.ic_phone,
                    hint = "Phone Number",
                    isPassword = false,
                    hasError = phoneNumError != NOT_ERROR,
                    errorText = getErrorText(phoneNumError)
                ) { viewModel.mobile.value = it }

                val passwordError = if (errors.value.contains(PASSWORD_ERROR)) PASSWORD_ERROR
                else if(errors.value.contains(PASSWORD_VALID_ERROR)) PASSWORD_VALID_ERROR
                else NOT_ERROR
                MainTextField(
                    edtValue = password.value,
                    icon = R.drawable.ic_password,
                    hint = "Password",
                    isPassword = true,
                    hasError = passwordError != NOT_ERROR,
                    errorText = getErrorText(passwordError)
                ) { viewModel.password.value = it }

                Button(
                    onClick = {
                        viewModel.errors.value = checkTextField(null, mobile, password, null)

                        if (viewModel.errors.value!!.isEmpty()) {
                            if (NetworkChecker(context).isInternetConnected){
                                viewModel.playLoadingAnim.value = true
                                signInEvent.invoke()
                            } else {
                                viewModel.loggingMessage.value = "Please check your internet connection"
                            }
                        }
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .padding(top = 18.dp, start = 28.dp, end = 28.dp)
                        .fillMaxWidth(),
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = blue)
                ) {
                    if (playLoadingAnim.value) {
                        LoadingAnimation()
                    } else {
                        Text(
                            text = "Sign In",
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.heightIn(24.dp))
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))
    LottieAnimation(
        composition = composition, iterations = LottieConstants.IterateForever,
        contentScale = ContentScale.Crop,
    )
}