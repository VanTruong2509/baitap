package com.example.navigationlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationlazycolumn.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHNavigationApp()
        }
    }
}

@Composable
fun UTHNavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "root") {
        composable("root") { RootScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("detail") { DetailScreen(navController) }
    }
}
