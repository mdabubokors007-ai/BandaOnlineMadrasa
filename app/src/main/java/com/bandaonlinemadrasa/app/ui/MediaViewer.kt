package com.bandaonlinemadrasa.app.ui

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.bandaonlinemadrasa.app.media.MediaKind
import com.bandaonlinemadrasa.app.media.kindOf

@Composable
fun MediaViewer(
    category: String,
    url: String,
    title: String,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        TextButton(onClick = onBack) { Text("← ফিরে যান") }

        when (kindOf(category)) {
            MediaKind.VIDEO -> VideoPlayer(url)
            MediaKind.IMAGE -> ImagePreview(url, title)
            MediaKind.DOCUMENT, MediaKind.SOFTWARE, MediaKind.UNKNOWN ->
                Text(
                    "এই ফাইলটি ডাউনলোড করে উপযুক্ত অ্যাপ দিয়ে খুলুন।",
                    modifier = Modifier.padding(20.dp)
                )
        }
    }
}

@Composable
private fun VideoPlayer(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
        factory = { context ->
            PlayerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                player = ExoPlayer.Builder(context).build().also { player ->
                    player.setMediaItem(MediaItem.fromUri(url))
                    player.prepare()
                    player.playWhenReady = false
                }
            }
        }
    )
}

@Composable
private fun ImagePreview(url: String, title: String) {
    // URL-to-image loader is intentionally kept behind this boundary so the
    // final build can choose an image library without exposing private URLs.
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Text("Image viewer প্রস্তুত — signed image URL দিয়ে পূর্ণ zoom/swipe viewer পরের UI pass-এ যুক্ত হবে।")
        Text(url, style = MaterialTheme.typography.bodySmall)
    }
}
