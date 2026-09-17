plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}
import java.util.Properties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
val props = Properties()
rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { props.load(it) }

android {
    namespace = "com.bandaonlinemadrasa.app"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.bandaonlinemadrasa.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 12
        versionName = "1.6.0"
        buildConfigField("String", "SUPABASE_URL", "\"${props.getProperty("SUPABASE_URL","")}\"")
        buildConfigField("String", "SUPABASE_PUBLISHABLE_KEY", "\"${props.getProperty("SUPABASE_PUBLISHABLE_KEY","")}\"")
    }
    buildFeatures { compose = true; buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    compilerOptions { jvmTarget = JvmTarget.JVM_11 }
}
dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.08.00")
    implementation(composeBom)
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation(platform("io.github.jan-tennert.supabase:bom:3.5.0"))
    implementation("io.github.jan-tennert.supabase:auth-kt")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:storage-kt")
    implementation("io.ktor:ktor-client-android:3.0.3")
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("androidx.media3:media3-exoplayer:1.11.1")
    implementation("androidx.media3:media3-ui:1.11.1")
}
