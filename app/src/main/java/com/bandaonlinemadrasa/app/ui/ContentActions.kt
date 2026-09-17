package com.bandaonlinemadrasa.app.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentActions(
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onDownload: () -> Unit,
    onShare: () -> Unit
) {
    Row {
        Button(onClick = onFavoriteToggle) {
            Text(if (isFavorite) "★ ফেভারিট" else "☆ ফেভারিট")
        }
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = onDownload) { Text("ডাউনলোড") }
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = onShare) { Text("শেয়ার") }
    }
}
