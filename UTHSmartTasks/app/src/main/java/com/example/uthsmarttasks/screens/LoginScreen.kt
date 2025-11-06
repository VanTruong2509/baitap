package com.example.uthsmarttasks.screens

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    // Google SignIn setup
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { firebaseTask ->
                    if (firebaseTask.isSuccessful) {
                        val user = auth.currentUser
                        val uid = user?.uid ?: ""
                        val userRef = FirebaseDatabase.getInstance().getReference("users/$uid")

                        val userInfo = mapOf(
                            "email" to user?.email,
                            "displayName" to user?.displayName,
                            "lastLogin" to System.currentTimeMillis()
                        )

                        userRef.updateChildren(userInfo)
                        navController.navigate("profile") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        message = "Google sign-in failed: ${firebaseTask.exception?.message}"
                    }
                }
        } catch (e: Exception) {
            message = "Google sign-in error: ${e.message}"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.height(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                loading = true
                message = ""
                auth.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        loading = false
                        if (task.isSuccessful) {
                            val uid = auth.currentUser?.uid ?: ""
                            val userRef = FirebaseDatabase.getInstance().getReference("users/$uid")

                            val user = mapOf(
                                "email" to email,
                                "lastLogin" to System.currentTimeMillis()
                            )

                            userRef.updateChildren(user).addOnCompleteListener {
                                navController.navigate("profile") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        } else {
                            message = "Login failed: ${task.exception?.message}"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        ) {
            Text(if (loading) "Logging in..." else "Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = { navController.navigate("forgot") }) {
            Text("Forgot Password?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Nút đăng nhập bằng Google
        Button(
            onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign in with Google")
        }
    }
}
