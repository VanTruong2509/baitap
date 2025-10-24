package com.example.uthsmarttasks.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R

@Composable
fun ConfirmScreen(navController: NavController, email: String, code: String, password: String) {
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
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Confirm Information",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Card hiển thị thông tin người dùng nhập
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                InfoRow(label = "Email:", value = email)
                Divider(modifier = Modifier.padding(vertical = 6.dp))
                InfoRow(label = "Code:", value = code)
                Divider(modifier = Modifier.padding(vertical = 6.dp))
                InfoRow(label = "Password:", value = password)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Nút xác nhận
        Button(
            onClick = {
                // Quay lại màn hình đầu tiên (Forgot Password)
                navController.popBackStack("forgot", inclusive = false)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit", fontSize = 18.sp)
        }
    }
}

// Hàm con hiển thị từng dòng thông tin
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.SemiBold)
        Text(value)
    }
}
