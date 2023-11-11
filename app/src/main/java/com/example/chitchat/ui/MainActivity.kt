package com.example.chitchat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chitchat.di.myModules
import com.example.chitchat.ui.features.signIn.SignInScreen
import com.example.chitchat.ui.features.signUp.SignUpScreen
import com.example.chitchat.ui.theme.MainAppTheme
import com.example.chitchat.utils.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = { modules(myModules) }) {
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
        }
    }
}


@Composable
fun CitChatUi() {
    val navController = rememberNavController()
    KoinNavHost(
        navController = navController,
        startDestination = MyScreens.SignInScreen.root
    ) {
        composable(MyScreens.SignUpScreen.root) {
            SignUpScreen()
        }

        composable(MyScreens.SignInScreen.root) {
            SignInScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
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