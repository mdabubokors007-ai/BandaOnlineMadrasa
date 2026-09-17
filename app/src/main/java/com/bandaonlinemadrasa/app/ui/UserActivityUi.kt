package com.bandaonlinemadrasa.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesHistoryPanel(
    favoriteCount: Int,
    recentCount: Int,
    downloadCount: Int,
    onFavorites: () -> Unit = {},
    onRecent: () -> Unit = {},
    onDownloads: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("আপনার লাইব্রেরি", style = MaterialTheme.typography.titleLarge)
        OutlinedButton(onClick = onFavorites, modifier = Modifier.fillMaxWidth()) {
            Text("❤️ পছন্দের তালিকা  •  $favoriteCount")
        }
        OutlinedButton(onClick = onRecent, modifier = Modifier.fillMaxWidth()) {
            Text("🕘 সম্প্রতি দেখা  •  $recentCount")
        }
        OutlinedButton(onClick = onDownloads, modifier = Modifier.fillMaxWidth()) {
            Text("📥 ডাউনলোড ইতিহাস  •  $downloadCount")
        }
    }
}

@Composable
fun ProfileSettingsPanel(
    displayName: String,
    onLanguage: () -> Unit = {},
    onNotifications: () -> Unit = {},
    onStorage: () -> Unit = {},
    onAbout: () -> Unit = {},
    onPrivacy: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(displayName, style = MaterialTheme.typography.headlineSmall)
        Text("বান্দা অনলাইন মাদরাসা", style = MaterialTheme.typography.bodyMedium)
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        SettingsRow("🌐 ভাষা / Language", onLanguage)
        SettingsRow("🔔 নোটিফিকেশন / Notifications", onNotifications)
        SettingsRow("💾 স্টোরেজ ও ডাউনলোড / Storage", onStorage)
        SettingsRow("ℹ️ আমাদের সম্পর্কে / About", onAbout)
        SettingsRow("🔒 Privacy Policy", onPrivacy)
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Logout")
        }
    }
}

@Composable
private fun SettingsRow(title: String, onClick: () -> Unit) {
    TextButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title)
            Text("›")
        }
    }
}
