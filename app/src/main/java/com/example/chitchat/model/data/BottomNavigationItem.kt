package com.example.chitchat.model.data

import com.example.chitchat.R
import com.example.chitchat.utils.MyScreens

data class BottomNavigationItem(
    val label : String = "",
    val icon : Int = 0,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Create Chat",
                icon = R.drawable.ic_create_chat,
                route = MyScreens.CreateChatScreen.route
            ),
            BottomNavigationItem(
                label = "Friends",
                icon = R.drawable.ic_friends,
                route = MyScreens.FriendsScreen.route
            ),
            BottomNavigationItem(
                label = "Chats",
                icon = R.drawable.ic_message_square,
                route = MyScreens.DialogsScreen.route
            ),
            BottomNavigationItem(
                label = "Notifications",
                icon = R.drawable.ic_notification,
                route = MyScreens.NotificationsScreen.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = R.drawable.ic_friends,
                route = MyScreens.ProfileScreen.route
            ),
        )
    }
}