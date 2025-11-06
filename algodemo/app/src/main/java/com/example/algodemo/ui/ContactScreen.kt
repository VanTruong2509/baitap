package com.example.algodemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algodemo.model.Contact

val sampleContacts = listOf(
    Contact("An", "0901234567"),
    Contact("Bình", "0987654321"),
    Contact("Chi", "0911222333"),
    Contact("Dũng", "0933444555"),
    Contact("Hà", "0944333222"),
    Contact("Tuấn", "0977555444"),
    Contact("Trường", "0334327457"),
    Contact("B", "0334527457"),
    Contact("C", "0344527457"),
    Contact("D", "0354527457"),

)

fun sortContacts(contacts: List<Contact>, ascending: Boolean = true): List<Contact> {
    return if (ascending) contacts.sortedBy { it.name }
    else contacts.sortedByDescending { it.name }
}

fun searchContacts(contacts: List<Contact>, query: String): List<Contact> {
    return contacts.filter {
        it.name.contains(query, ignoreCase = true) ||
                it.phone.contains(query)
    }
}

@Composable
fun ContactScreen(onBack: () -> Unit) {
    var contacts by remember { mutableStateOf(sampleContacts) }
    var query by remember { mutableStateOf("") }
    var ascending by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("🔍 Danh bạ điện thoại", fontSize = 22.sp)
        Spacer(Modifier.height(12.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("⬅️ Quay lại trang chủ")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Nhập tên hoặc số điện thoại") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                ascending = !ascending
                contacts = sortContacts(contacts, ascending)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (ascending) "⬆️ Sắp xếp A–Z" else "⬇️ Sắp xếp Z–A")
        }

        Spacer(Modifier.height(16.dp))

        val filtered = searchContacts(contacts, query)
        LazyColumn {
            items(filtered) { contact ->
                ContactItem(contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(contact.name, fontSize = 18.sp)
            Text(contact.phone, fontSize = 16.sp)
        }
    }
}
