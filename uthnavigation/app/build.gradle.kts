plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") // ⚡ BẮT BUỘC với Kotlin 2.0+
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.example.uthnavigation"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.uthnavigation"
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

    // 🔹 Bật Compose
    buildFeatures {
        compose = true
    }

    // 🔹 Cấu hình compiler cho Compose
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // AndroidX cơ bản
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)

    implementation(platform("androidx.compose:compose-bom:2024.10.00"))


    // 🔹 Jetpack Compose BOM (quản lý phiên bản)
    implementation(platform("androidx.compose:compose-bom:2024.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // 🔹 Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // 🔹 Foundation (cho HorizontalPager)
    implementation("androidx.compose.foundation:foundation")

    // 🔹 Lifecycle Compose (cho LaunchedEffect, rememberCoroutineScope, v.v.)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
