# Build verification status

The project has now been repaired so the Android module has:
- a launcher `MainActivity`
- required theme resources
- Supabase client provider
- OAuth deep-link manifest entry
- valid BuildConfig property quoting
- Java/Kotlin target compatibility

A live release build still requires Gradle/Android SDK plus the user's Supabase URL/publishable key and Google OAuth configuration. Those account-specific values are intentionally not fabricated.
