package com.bandaonlinemadrasa.app.auth

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

class AuthSessionManager(private val client: SupabaseClient) {
    fun currentUserId(): String? =
        client.auth.currentSessionOrNull()?.user?.id

    suspend fun signOut() {
        client.auth.signOut()
    }
}
