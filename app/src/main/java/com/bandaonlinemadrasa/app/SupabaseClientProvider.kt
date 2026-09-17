package com.bandaonlinemadrasa.app

import android.content.Intent
import com.bandaonlinemadrasa.app.core.AppConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.auth.ExternalAuthAction
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClientProvider {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_PUBLISHABLE_KEY
    ) {
        install(Auth) {
            flowType = FlowType.PKCE
            scheme = "bandaonlinemadrasa"
            host = "login-callback"
            defaultExternalAuthAction = ExternalAuthAction.CustomTabs()
        }
        install(Postgrest)
        install(Storage)
    }
}

// ✅ Extension function যা deep link handle করে
fun SupabaseClient.handleDeeplinks(intent: Intent?) {
    intent ?: return
    val data = intent.data ?: return
    if (data.scheme == "bandaonlinemadrasa" && data.host == "login-callback") {
        try {
            this.auth.parseDeeplink(data.toString())
        } catch (t: Throwable) {
            // ignore invalid deeplinks
        }
    }
}
