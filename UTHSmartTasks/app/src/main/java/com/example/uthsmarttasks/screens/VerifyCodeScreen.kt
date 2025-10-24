package com.example.uthsmarttasks.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R

@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    val codeDigits = remember { mutableStateListOf("", "", "", "", "") }
    val focusRequesters = List(5) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.height(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Verify Code", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("A code was sent to $email")

        Spacer(modifier = Modifier.height(24.dp))

        // Hàng chứa 5 ô nhập mã
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            codeDigits.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        if (it.length <= 1 && it.all { ch -> ch.isDigit() }) {
                            codeDigits[index] = it
                            if (it.isNotEmpty() && index < 4) {
                                focusRequesters[index + 1].requestFocus()
                            } else if (index == 4 && it.isNotEmpty()) {
                                focusManager.clearFocus()
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .width(55.dp)
                        .height(60.dp)
                        .focusRequester(focusRequesters[index]),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (index == 4) ImeAction.Done else ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            if (index < 4) focusRequesters[index + 1].requestFocus()
                        },
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        val code = codeDigits.joinToString("")

        Button(
            onClick = {
                if (code.length == 5) {
                    navController.navigate("createNew/$email/$code")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
