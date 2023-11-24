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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.chitchat.ui.features.signIn.LoadingAnimation
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.lightGray
import com.example.chitchat.ui.theme.lightGrayishBlue
import com.example.chitchat.ui.theme.red
import com.example.chitchat.ui.theme.shapes
import com.example.chitchat.ui.theme.textBlack
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.transparent
import com.example.chitchat.ui.theme.white
import com.example.chitchat.utils.CONFIRM_PASSWORD_ERROR
import com.example.chitchat.utils.NUMBER_ERROR
import com.example.chitchat.utils.NUMBER_VALID_ERROR
import com.example.chitchat.utils.MyScreens
import com.example.chitchat.utils.NAME_ERROR
import com.example.chitchat.utils.NOT_ERROR
import com.example.chitchat.utils.PASSWORD_ERROR
import com.example.chitchat.utils.PASSWORD_MATCH_ERROR
import com.example.chitchat.utils.PASSWORD_VALID_ERROR
import dev.burnoo.cokoin.navigation.getNavController
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

    clearInputs(viewModel)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MainCardView(viewModel) {
            viewModel.playLoadingAnim.value = false
        }

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
                    navigation.navigate(MyScreens.SignInScreen.route)
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
    val mobile = viewModel.mobile.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val confirmPassword = viewModel.confirmPassword.observeAsState("")
    val errors = viewModel.errors.observeAsState(ArrayList())
    val playLoadingAnim = viewModel.playLoadingAnim.observeAsState(false)
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
                    isPassword = false,
                    hasError = errors.value.contains(NAME_ERROR),
                    errorText = getErrorText(NAME_ERROR)
                ) { viewModel.name.value = it }

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

                val confirmError = if (errors.value.contains(CONFIRM_PASSWORD_ERROR)) CONFIRM_PASSWORD_ERROR else if (errors.value.contains(PASSWORD_MATCH_ERROR )) PASSWORD_MATCH_ERROR else NOT_ERROR
                MainTextField(
                    edtValue = confirmPassword.value,
                    icon = R.drawable.ic_password,
                    hint = "Confirm Password",
                    isPassword = true,
                    hasError = confirmError != NOT_ERROR,
                    errorText = getErrorText(confirmError)
                ) { viewModel.confirmPassword.value = it }

                Button(
                    onClick = {
                        viewModel.errors.value =
                            checkTextField(name, mobile, password, confirmPassword)

                        if (viewModel.errors.value!!.isEmpty()) {
                            viewModel.playLoadingAnim.value = true
                            signUpEvent.invoke()
                        }
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 28.dp, end = 28.dp),
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = blue)
                ) {
                    if (playLoadingAnim.value) {
                        LoadingAnimation()
                    } else {
                        Text(
                            text = "Create Account",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    edtValue: String,
    icon: Int,
    hint: String,
    isPassword: Boolean,
    hasError: Boolean,
    errorText: String,
    onValueChanges: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        label = { Text(text = hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, start = 28.dp, end = 28.dp),
        shape = shapes.medium,
        placeholder = { if (icon == R.drawable.ic_phone){Text(text = "9123456789")} },
        leadingIcon = {
            Icon(
                painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
                ) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = lightGrayishBlue,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            errorIndicatorColor = transparent,
            disabledIndicatorColor = transparent,
            focusedLabelColor = textBlack,
            unfocusedLabelColor = textGray,
            errorLabelColor = textGray,
            focusedLeadingIconColor = textBlack,
            unfocusedLeadingIconColor = textGray,
            focusedTrailingIconColor = textBlack,
            unfocusedTrailingIconColor = textGray,
            errorTrailingIconColor = textGray,
            placeholderColor = textGray
        ),
        visualTransformation = if (passwordVisible || !isPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType =
            if (isPassword) { KeyboardType.Password
            } else if (icon.equals(R.drawable.ic_phone)){
                KeyboardType.Phone
            } else {
                KeyboardType.Text
            }
        ),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible) painterResource(id = R.drawable.ic_visible)
                else painterResource(id = R.drawable.ic_invisible)

                Icon(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    }
                )
            }
        },
        isError = hasError,
        supportingText = {
            if (hasError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorText,
                    color = red
                )
            }
        },
    )
}

fun checkTextField(
    name: State<String>?,
    number: State<String>,
    password: State<String>,
    configPassword: State<String>?
): ArrayList<Int> {
    val errorList = ArrayList<Int>()
    if (number.value.isEmpty() && number.value.isBlank()) {
        errorList.add(NUMBER_ERROR)
    } else {
        errorList.remove(NUMBER_ERROR)

        if (number.value.length > 10 || number.value.length < 9)
            errorList.add(NUMBER_VALID_ERROR)
        else
            errorList.remove(NUMBER_VALID_ERROR)
    }

    if (password.value.isEmpty() && password.value.isBlank()) {
        errorList.add(PASSWORD_ERROR)
    } else {
        errorList.remove(PASSWORD_ERROR)
        if (password.value.length < 8) {
            errorList.add(PASSWORD_VALID_ERROR)
        } else {
            errorList.remove(PASSWORD_VALID_ERROR)
        }
    }

    if (name != null) {
        if (name.value.isEmpty() && name.value.isBlank()) {
            errorList.add(NAME_ERROR)
        } else {
            errorList.remove(NAME_ERROR)
        }
    }

    if (configPassword != null) {
        if (configPassword.value.isEmpty() && configPassword.value.isBlank()) {
            errorList.add(CONFIRM_PASSWORD_ERROR)
        } else {
            errorList.remove(CONFIRM_PASSWORD_ERROR)

            if (password.value != configPassword.value) {
                errorList.add(PASSWORD_MATCH_ERROR)
            } else {
                errorList.remove(PASSWORD_MATCH_ERROR)
            }
        }
    }
    return errorList
}

fun getErrorText(errorType: Int): String {
    when (errorType) {
        NAME_ERROR -> return "Please enter your Name"
        NUMBER_ERROR -> return "Please enter your Phone number"
        NUMBER_VALID_ERROR -> return "Please enter a valid Phone number"
        PASSWORD_ERROR -> return "Please enter your Password"
        CONFIRM_PASSWORD_ERROR -> return "Please enter confirm password"
        PASSWORD_MATCH_ERROR -> return "The confirm password does not match"
        PASSWORD_VALID_ERROR -> return "The password should be more then 8 character"
    }
    return ""
}

fun clearInputs(viewModel: SignUpViewModel) {
    viewModel.name.value = ""
    viewModel.mobile.value = ""
    viewModel.password.value = ""
    viewModel.confirmPassword.value = ""
}