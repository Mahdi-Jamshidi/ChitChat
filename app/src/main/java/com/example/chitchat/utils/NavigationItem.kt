package com.example.chitchat.utils

import com.example.chitchat.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object CreateChatScreen : NavigationItem("createChatScreen", R.drawable.ic_create_chat, "Create Chat")
    object FriendsScreen : NavigationItem("friendsScreen", R.drawable.ic_friends, "Friends")
    object DialogsScreen : NavigationItem("dialogsScreen", R.drawable.ic_message_square, "Chats")
    object NotificationsScreen : NavigationItem("notificationsScreen", R.drawable.ic_notification, "Notifications")
    object ProfileScreen : NavigationItem("profileScreen", R.drawable.ic_user, "Profile")
}
