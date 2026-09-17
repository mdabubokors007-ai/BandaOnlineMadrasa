package com.bandaonlinemadrasa.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserActivitySection(
    title: String,
    emptyMessage: String,
    items: List<String>
) {
    Column(Modifier.padding(16.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        if (items.isEmpty()) {
            Text(emptyMessage, style = MaterialTheme.typography.bodyMedium)
        } else {
            items.forEach { Text("• $it", style = MaterialTheme.typography.bodyMedium) }
        }
    }
}
