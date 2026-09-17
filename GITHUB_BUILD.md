# GitHub Build

এই version-এ GitHub Actions দিয়ে ফোন থেকেই APK build করা যাবে।

## Repository Secrets
Settings → Secrets and variables → Actions → New repository secret:
- SUPABASE_URL
- SUPABASE_PUBLISHABLE_KEY

তারপর Actions → Build APK → Run workflow। Build শেষ হলে Artifacts থেকে `app-debug.apk` নামানো যাবে।

## Google Login
Supabase Dashboard → Authentication → Providers → Google → Enable। Google OAuth client ID/secret configure করুন।
Authentication → URL Configuration → Redirect URLs-এ যোগ করুন:
`bandaonlinemadrasa://login-callback`

এই version-এ INTERNET permission, Google OAuth call এবং Android deep-link callback handling যোগ করা হয়েছে।
