plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.algodemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.algodemo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 🔹 Kích hoạt Compose
    buildFeatures {
        compose = true
    }

    // 🔹 Compose compiler tự động quản lý (vì bạn dùng plugin compose mới)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // --- Jetpack Compose core ---
    implementation("androidx.activity:activity-compose:1.9.3") // 👈 để có setContent()
    implementation("androidx.compose.ui:ui:1.7.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
    implementation("androidx.compose.material3:material3:1.3.0") // 👈 để có MaterialTheme, Surface, Text
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.3")

    // --- AndroidX cơ bản ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.compose.foundation:foundation:1.7.3")


    // --- Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
