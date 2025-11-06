package com.example.tuan5.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun FirebaseLoginScreen(auth: FirebaseAuth, googleSignInClient: GoogleSignInClient) {
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            loading = true
            auth.signInWithCredential(credential)
                .addOnCompleteListener { signInTask ->
                    loading = false
                    if (signInTask.isSuccessful) {
                        val user = auth.currentUser
                        message = "Success!\nHi ${user?.email}\nWelcome to tuan5"
                    } else {
                        message = "Google Sign-In Failed\n${signInTask.exception?.message}"
                    }
                }
        } catch (e: ApiException) {
            message = "Google Sign-In Failed\nUser canceled the Google sign-in process."
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val intent = googleSignInClient.signInIntent
                    launcher.launch(intent)
                },
                enabled = !loading,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text("Login by Gmail")
            }

            Spacer(modifier = Modifier.height(20.dp))

            when {
                loading -> CircularProgressIndicator()
                message.isNotEmpty() -> Text(message)
            }
        }
    }
}
