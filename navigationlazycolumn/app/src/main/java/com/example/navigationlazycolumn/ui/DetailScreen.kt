package com.example.navigationlazycolumn.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "\"The only way to do great work is to love what you do.\"",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.popBackStack("root", inclusive = false) }) {
            Text("BACK TO ROOT")
        }
    }
}
