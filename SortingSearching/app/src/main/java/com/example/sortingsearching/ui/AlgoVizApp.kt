package com.example.sortingsearching.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun AlgoVizApp() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sorting & Searching Demo") })
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    label = { Text("Sorting") },
                    icon = {}
                )
                BottomNavigationItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("Graph Search") },
                    icon = {}
                )
                BottomNavigationItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    label = { Text("Array Search") },
                    icon = {}
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> SortingDemo(Modifier.padding(innerPadding))
            1 -> GraphDemo(Modifier.padding(innerPadding))
            2 -> ArraySearchDemo(Modifier.padding(innerPadding))
        }
    }
}
