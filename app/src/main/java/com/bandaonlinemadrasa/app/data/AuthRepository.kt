package com.bandaonlinemadrasa.app.data

import com.bandaonlinemadrasa.app.SupabaseClientProvider
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google

class AuthRepository {
    private val client = SupabaseClientProvider.client

    suspend fun signInWithGoogle() {
        client.auth.signInWith(Google)
    }

    suspend fun signOut() {
        client.auth.signOut()
    }
}
