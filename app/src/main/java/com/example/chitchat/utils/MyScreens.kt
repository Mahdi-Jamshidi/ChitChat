package com.example.chitchat.utils

sealed class MyScreens(val route: String){

    object MainScreen : MyScreens("mainScreen")
    object SignUpScreen : MyScreens("signUpScreen")
    object SignInScreen : MyScreens("signInScreen")
    object DialogsScreen : MyScreens("dialogsScreen")
    object CreateChatScreen : MyScreens("createChatScreen")
    object FriendsScreen : MyScreens("friendsScreen")
    object NotificationsScreen : MyScreens("notificationsScreen")
    object ProfileScreen : MyScreens("profileScreen")
}
