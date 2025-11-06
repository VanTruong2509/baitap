package com.example.algodemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algodemo.ui.ContactScreen
import com.example.algodemo.ui.SortingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AlgoDemoApp()
                }
            }
        }
    }
}

@Composable
fun AlgoDemoApp() {
    var currentScreen by remember { mutableStateOf("menu") }

    when (currentScreen) {
        "menu" -> MainMenu(onNavigate = { currentScreen = it })
        "sorting" -> SortingScreen(onBack = { currentScreen = "menu" })
        "contacts" -> ContactScreen(onBack = { currentScreen = "menu" })
    }
}

@Composable
fun MainMenu(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📚 Demo", fontSize = 24.sp)
        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { onNavigate("sorting") },
            modifier = Modifier.fillMaxWidth()
        ) { Text("⚙️ Sắp xếp (Sorting)") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onNavigate("contacts") },
            modifier = Modifier.fillMaxWidth()
        ) { Text("🔍 Tìm kiếm Danh bạ (Searching)") }
    }
}
