package com.example.jetpackcomposeuicomponents.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UiComponentsScreen() {
    var checked by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0.5f) }
    var selectedRadio by remember { mutableStateOf("Option 1") }
    var rating by remember { mutableStateOf(3) }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🧱 UI Components Demo", style = MaterialTheme.typography.headlineSmall)

        Button(onClick = {}) {
            Text("Click Me")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Text("Checkbox is ${if (checked) "checked" else "unchecked"}")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SeekBar value: ${(sliderValue * 100).toInt()}%")
            Slider(value = sliderValue, onValueChange = { sliderValue = it })
        }

        Column {
            Text("Choose one:")
            options.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedRadio == option,
                        onClick = { selectedRadio = option }
                    )
                    Text(option)
                }
            }
        }

        Box {
            Button(onClick = { expanded = !expanded }) {
                Text(selectedOption)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Row {
            for (i in 1..5) {
                IconButton(onClick = { rating = i }) {
                    Icon(
                        imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Rating $i"
                    )
                }
            }
        }
        Text("Your Rating: $rating / 5")
    }
}
