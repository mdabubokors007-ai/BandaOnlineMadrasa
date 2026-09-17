package com.bandaonlinemadrasa.app.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bandaonlinemadrasa.app.ContentItem
import com.bandaonlinemadrasa.app.media.AppDownloadManager
import com.bandaonlinemadrasa.app.media.ShareHelper

@Composable
fun ContentActions(
    context: Context,
    item: ContentItem,
    signedUrl: String?,
    onOpen: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Button(
            onClick = onOpen,
            modifier = Modifier.fillMaxWidth()
        ) { Text("দেখুন / Preview") }

        Button(
            enabled = item.downloadEnabled && signedUrl != null,
            onClick = {
                signedUrl?.let {
                    AppDownloadManager(context).enqueue(it, item.name)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (item.downloadEnabled) "ডাউনলোড" else "ডাউনলোড বন্ধ")
        }

        OutlinedButton(
            enabled = signedUrl != null,
            onClick = {
                signedUrl?.let { ShareHelper.shareLink(context, item.name, it) }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("শেয়ার") }
    }
}
