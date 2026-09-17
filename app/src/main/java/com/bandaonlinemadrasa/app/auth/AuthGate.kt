package com.bandaonlinemadrasa.app.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthGate(
    sessionManager: AuthSessionManager,
    loginContent: @Composable () -> Unit,
    authenticatedContent: @Composable (userId: String) -> Unit
) {
    var userId by remember { mutableStateOf<String?>(null) }
    var ready by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        userId = sessionManager.currentUserId()
        ready = true
    }

    when {
        !ready -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        userId != null -> authenticatedContent(userId!!)
        else -> loginContent()
    }
}
