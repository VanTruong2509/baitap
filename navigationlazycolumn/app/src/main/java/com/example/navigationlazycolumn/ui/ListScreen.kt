package com.example.navigationlazycolumn.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController) {
    val items = List(1_000_000) { index -> "Item #$index" }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "LazyColumn",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail") }
                        .padding(16.dp)
                ) {
                    Text(item)
                }
            }
        }
    }
}
