# বান্দা অনলাইন মাদরাসা v1.6.0

এই সংস্করণটি আগের login আটকে থাকার সমস্যার জন্য পুনর্গঠিত হয়েছে।
- Google OAuth button এখন বাস্তব Supabase `signInWith(Google)` call করে
- Android OAuth deep-link callback `handleDeeplinks(intent)` দিয়ে handle করে
- `onNewIntent`-এ callback handle করে
- INTERNET permission যোগ করা হয়েছে
- নির্বাচিত final logo app-এ এবং launcher icon হিসেবে যোগ করা হয়েছে
- Login → Home → Search → Favorites → Profile → Details flow রাখা হয়েছে
- GitHub Actions workflow দিয়ে APK build করা যায়

Google login সম্পূর্ণ চালু করতে Supabase/Google provider configuration অবশ্যই করতে হবে।
