# বান্দা অনলাইন মাদরাসা — Final Release Checklist

## Code
- [x] Android project structure
- [x] Compose UI foundation
- [x] Supabase client foundation
- [x] Google OAuth deep-link foundation
- [x] Private storage foundation
- [x] Content library/search foundation
- [x] Media/download foundation
- [x] Favorites/history foundation
- [x] Profile/settings foundation

## Must be completed with the owner's accounts/device
- [ ] Put Supabase URL + publishable key in `local.properties`
- [ ] Configure Google provider in Supabase Auth
- [ ] Run Gradle sync in Android Studio
- [ ] Build debug APK
- [ ] Test login, library, playback, download, favorites and logout
- [ ] Configure release signing
- [ ] Build signed AAB
- [ ] Prepare Play Console listing and policy declarations
- [ ] Upload AAB and complete Play review requirements

## Security
- Never put a Supabase service-role key in the Android app.
- Keep storage buckets private for protected content.
- Use authenticated access/signed URLs for private files.
