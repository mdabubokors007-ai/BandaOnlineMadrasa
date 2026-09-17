# বান্দা অনলাইন মাদরাসা (Banda Online Madrasa) v1.5.0

Android অ্যাপ — Compose + Supabase foundation।  
এই প্যাক **GitHub Actions** দিয়ে Debug APK বানানোর জন্য রেডি।

---

## ফোনে GitHub দিয়ে APK বানানো (ফ্রি)

### ১) GitHub অ্যাকাউন্ট
- ফোনে [github.com](https://github.com) খুলে সাইন আপ করুন।

### ২) নতুন রিপোজিটরি
- **New repository** → নাম: `BandaOnlineMadrasa` → **Public** → Create।

### ৩) কোড আপলোড
- রিপোর **Add file → Upload files**
- এই ZIP আনজিপ করে **সব ফাইল/ফোল্ডার** সিলেক্ট করে আপলোড করুন  
  (`.github` ফোল্ডারসহ)।
- Commit changes।

### ৪) (ঐচ্ছিক) Supabase Secrets
- Repo → **Settings → Secrets and variables → Actions**
- Add:
  - `SUPABASE_URL`
  - `SUPABASE_PUBLISHABLE_KEY`  
খালি রাখলেও APK বানবে; শুধু cloud ফিচার কাজ করবে না।

### ৫) APK বিল্ড
- **Actions** ট্যাব → **Build Debug APK** → **Run workflow**
- শেষ হলে **Artifacts** থেকে `banda-online-madrasa-debug-apk` ডাউনলোড করুন।
- ZIP খুলে `.apk` ফাইল ইনস্টল করুন (Unknown sources অনুমতি দিন)।

---

## কম্পিউটারে বিল্ড (যদি থাকে)

```bash
# local.properties বানান
cp local.properties.example local.properties
# sdk.dir এবং Supabase মান বসান

chmod +x gradlew
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/
```

Android Studio তে Open করে Sync → Runও করা যায়।

---

## প্রজেক্টে যা আছে
- Compose UI foundation (মূল স্ক্রিন)
- Supabase client / Auth / Storage hooks
- Media (ExoPlayer), Download, Share helpers
- Favorites / recent / download history repository stubs
- OAuth deep-link (`bandaonlinemadrasa://login-callback`)
- GitHub Actions: `.github/workflows/build-apk.yml`

## রিলিজ ব্লকার (মালিকের অ্যাকাউন্ট লাগে)
1. Supabase URL + publishable key  
2. Google OAuth (Supabase Auth)  
3. Signed release / Play Console  

**কখনো service-role key অ্যাপে রাখবেন না।**

---

## প্যাকেজ
- `applicationId`: `com.bandaonlinemadrasa.app`
- `versionName`: `1.5.0` (`versionCode` 15)
- `minSdk` 26 · `targetSdk` / `compileSdk` 35
