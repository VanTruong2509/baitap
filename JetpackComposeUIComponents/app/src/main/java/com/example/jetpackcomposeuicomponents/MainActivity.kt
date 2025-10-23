package com.example.jetpackcomposeuicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.*
import com.example.jetpackcomposeuicomponents.ui.UiComponentsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "welcome") {
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("list") { ComponentsListScreen(navController) }
                    composable("text") { TextDetailScreen() }
                    composable("image") { ImageScreen() }
                    composable("textfield") { TextFieldScreen() }
                    composable("row") { RowLayoutScreen() }
                    composable("uicomponents") { UiComponentsScreen() }
                }
            }
        }
    }
}
