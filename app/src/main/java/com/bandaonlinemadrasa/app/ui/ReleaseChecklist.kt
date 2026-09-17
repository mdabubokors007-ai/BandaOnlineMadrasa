package com.bandaonlinemadrasa.app.ui

data class ReleaseCheck(
    val title: String,
    val completed: Boolean,
    val note: String = ""
)

object ReleaseChecklist {
    fun current(): List<ReleaseCheck> = listOf(
        ReleaseCheck("Project structure", true),
        ReleaseCheck("Private storage foundation", true),
        ReleaseCheck("Content library foundation", true),
        ReleaseCheck("Media/download foundation", true),
        ReleaseCheck("Favorites/history foundation", true),
        ReleaseCheck("Session-aware authentication", true),
        ReleaseCheck("Supabase project credentials", false, "Owner action required"),
        ReleaseCheck("Google OAuth configuration", false, "Owner action required"),
        ReleaseCheck("Production build/signing", false, "Final build step"),
        ReleaseCheck("Real-device QA", false, "Final test step"),
        ReleaseCheck("Play Console setup", false, "Publishing step")
    )
}
