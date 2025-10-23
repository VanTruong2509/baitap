package com.example.jetpackcompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.font.FontStyle


@Composable
fun TextDetailScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            buildAnnotatedString {
                append("The ")
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick ") }
                withStyle(SpanStyle(color = androidx.compose.ui.graphics.Color(0xFFB8860B))) { append("Brown ") }
                append("fox ")
                withStyle(SpanStyle(letterSpacing = 2.sp)) { append("jumps ") }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("over ") }
                append("the ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("lazy dog.") }
            }
        )
    }
}
