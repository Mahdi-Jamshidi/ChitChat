package com.example.chitchat.utils

sealed class MyScreens(val root: String){

    object MainScreen : MyScreens("mainScreen")
    object SignUpScreen : MyScreens("signUpScreen")
    object SignInScreen : MyScreens("signInScreen")
}
