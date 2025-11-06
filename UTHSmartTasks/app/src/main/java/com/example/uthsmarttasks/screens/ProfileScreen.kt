package com.example.uthsmarttasks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ProfileScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid

    var email by remember { mutableStateOf(auth.currentUser?.email ?: "") }
    var lastLogin by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }

    // ✅ Load user data from Firebase Realtime Database
    LaunchedEffect(Unit) {
        uid?.let {
            FirebaseDatabase.getInstance().getReference("users/$uid")
                .get()
                .addOnSuccessListener { snapshot ->
                    email = snapshot.child("email").value.toString()
                    lastLogin = snapshot.child("lastLogin").value.toString()
                    loading = false
                }
                .addOnFailureListener {
                    loading = false
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (loading) {
            CircularProgressIndicator()
        } else {
            Text("Welcome!", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Email: $email")
            Spacer(modifier = Modifier.height(6.dp))
            Text("Last login: $lastLogin")
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                auth.signOut()
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }) {
                Text("Logout")
            }
        }
    }
}
