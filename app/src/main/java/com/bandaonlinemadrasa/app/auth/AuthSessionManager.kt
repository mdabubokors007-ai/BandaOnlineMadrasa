package com.bandaonlinemadrasa.app.auth

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow

class AuthSessionManager(private val client: SupabaseClient) {

    val sessionStatus: Flow<SessionStatus>
        get() = client.auth.sessionStatus

    fun currentUserId(): String? =
        client.auth.currentSessionOrNull()?.user?.id

    suspend fun signOut() {
        client.auth.signOut()
    }
}
