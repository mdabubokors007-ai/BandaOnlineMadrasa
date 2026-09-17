package com.bandaonlinemadrasa.app.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class UserActivityRepository(private val client: SupabaseClient) {

    suspend fun addFavorite(userId: String, contentId: String) {
        client.from("favorites").upsert(
            mapOf(
                "user_id" to userId,
                "content_id" to contentId
            )
        )
    }

    suspend fun removeFavorite(userId: String, contentId: String) {
        client.from("favorites").delete {
            filter {
                eq("user_id", userId)
                eq("content_id", contentId)
            }
        }
    }

    suspend fun recordRecentView(userId: String, contentId: String) {
        client.from("recent_views").upsert(
            mapOf(
                "user_id" to userId,
                "content_id" to contentId
            )
        )
    }

    suspend fun recordDownload(userId: String, contentId: String) {
        client.from("download_history").upsert(
            mapOf(
                "user_id" to userId,
                "content_id" to contentId
            )
        )
    }
}
