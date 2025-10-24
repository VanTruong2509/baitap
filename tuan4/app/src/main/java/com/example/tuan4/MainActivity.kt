package com.example.tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.tuan4.ui.screens.*
import com.example.tuan4.ui.theme.LibraryAppTheme
import com.example.tuan4.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                val viewModel = LibraryViewModel()
                var selectedTab by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Home, null) },
                                label = { Text("Quản lý") },
                                selected = selectedTab == 0,
                                onClick = { selectedTab = 0 }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.List, null) },
                                label = { Text("DS Sách") },
                                selected = selectedTab == 1,
                                onClick = { selectedTab = 1 }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Person, null) },
                                label = { Text("Sinh viên") },
                                selected = selectedTab == 2,
                                onClick = { selectedTab = 2 }
                            )
                        }
                    }
                ) { innerPadding ->   // ✅ đổi tên biến để dễ hiểu
                    Surface(modifier = Modifier.padding(innerPadding)) {   // ✅ dùng Modifier từ compose.foundation.layout
                        when (selectedTab) {
                            0 -> LibraryScreen(viewModel)
                            1 -> BookListScreen(viewModel)
                            2 -> StudentListScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}
