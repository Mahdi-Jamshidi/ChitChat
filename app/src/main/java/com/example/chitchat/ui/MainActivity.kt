package com.example.chitchat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chitchat.di.myModules
import com.example.chitchat.model.repository.TokenInMemory
import com.example.chitchat.model.repository.auth.AuthRepository
import com.example.chitchat.ui.features.screens.CreateChatScreen
import com.example.chitchat.ui.features.chatDialogs.DialogsScreen
import com.example.chitchat.ui.features.screens.FriendsScreen
import com.example.chitchat.ui.features.screens.NotificationsScreen
import com.example.chitchat.ui.features.screens.ProfileScreen
import com.example.chitchat.ui.features.signIn.SignInScreen
import com.example.chitchat.ui.features.signUp.SignUpScreen
import com.example.chitchat.ui.theme.MainAppTheme
import com.example.chitchat.ui.theme.blue
import com.example.chitchat.ui.theme.textGray
import com.example.chitchat.ui.theme.white
import com.example.chitchat.utils.MyScreens
import com.example.chitchat.utils.NavigationItem
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {
                MainAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val authRepository: AuthRepository = get()
                        authRepository.loadToken()
                        CitChatUi()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CitChatUiPreview() {
    MainAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CitChatUi()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitChatUi() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            if (showBottomNavigation(navBackStackEntry)) BottomNavigationBar(navController = navController)
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            Navigation(navController)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {

    KoinNavHost(
        navController = navController,
        startDestination = MyScreens.DialogsScreen.route
    ) {
        composable(MyScreens.DialogsScreen.route) {
            if (TokenInMemory.token != null) {
                DialogsScreen()
            } else {
                SignInScreen()
            }
        }
        composable(MyScreens.SignUpScreen.route) {
            SignUpScreen()
        }
        composable(MyScreens.SignInScreen.route) {
            SignInScreen()
        }
        composable(MyScreens.CreateChatScreen.route) {
            CreateChatScreen()
        }
        composable(MyScreens.DialogsScreen.route) {
            DialogsScreen()
        }
        composable(MyScreens.FriendsScreen.route) {
            FriendsScreen()
        }
        composable(MyScreens.NotificationsScreen.route) {
            NotificationsScreen()
        }
        composable(MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.CreateChatScreen,
        NavigationItem.FriendsScreen,
        NavigationItem.DialogsScreen,
        NavigationItem.NotificationsScreen,
        NavigationItem.ProfileScreen
    )

    NavigationBar(
        containerColor = white,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = false,
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = navigationItem.title,
                        tint = if (currentRoute == navigationItem.route) blue else textGray
                    )
                },
                onClick = {
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

fun showBottomNavigation(navBackStackEntry: NavBackStackEntry?): Boolean {
    val currentRoute = navBackStackEntry?.destination?.route
    return currentRoute == NavigationItem.CreateChatScreen.route ||
            currentRoute == NavigationItem.FriendsScreen.route ||
            currentRoute == NavigationItem.DialogsScreen.route ||
            currentRoute == NavigationItem.NotificationsScreen.route ||
            currentRoute == NavigationItem.ProfileScreen.route
}