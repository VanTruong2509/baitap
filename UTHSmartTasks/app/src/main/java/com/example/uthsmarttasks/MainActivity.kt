package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.uthsmarttasks.screens.*
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksTheme {
                SmartTasksApp()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SmartTasksApp() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = "forgot"
    ) {
        composable("forgot") { ForgotPasswordScreen(navController) }

        composable("verify/{email}") { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController, email)
        }

        composable("createNew/{email}/{code}") { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            val code = backStack.arguments?.getString("code") ?: ""
            CreateNewPasswordScreen(navController, email, code)
        }

        composable("confirm/{email}/{code}/{password}") { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            val code = backStack.arguments?.getString("code") ?: ""
            val password = backStack.arguments?.getString("password") ?: ""
            ConfirmScreen(navController, email, code, password)
        }
    }
}
