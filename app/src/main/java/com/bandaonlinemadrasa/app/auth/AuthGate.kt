package com.bandaonlinemadrasa.app.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.jan.supabase.auth.status.SessionStatus

@Composable
fun AuthGate(
    sessionManager: AuthSessionManager,
    loginContent: @Composable () -> Unit,
    authenticatedContent: @Composable (userId: String) -> Unit
) {
    val status by sessionManager.sessionStatus.collectAsState(
        initial = SessionStatus.Initializing
    )

    when (status) {
        SessionStatus.Initializing -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is SessionStatus.Authenticated -> {
            val userId = sessionManager.currentUserId()
            if (userId != null) {
                authenticatedContent(userId)
            } else {
                loginContent()
            }
        }

        is SessionStatus.NotAuthenticated,
        is SessionStatus.RefreshFailure -> {
            loginContent()
        }
    }
}
