plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.jetpackcompose"
    compileSdk = 36 // Giữ nguyên

    defaultConfig {
        applicationId = "com.example.jetpackcompose"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // --- Cấu hình Jetpack Compose ---
    buildFeatures {
        compose = true
    }

    composeOptions {
        // Đảm bảo phiên bản này khớp với phiên bản Kotlin của bạn
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    // --- Dependencies View System truyền thống (Giữ nguyên) ---
    implementation(libs.androidx.core.ktx)

    // Giữ lại Activity cho hàm setContent
    implementation(libs.androidx.activity)

    // --- Bổ sung dependencies cho Jetpack Compose (ĐÃ THÊM PHIÊN BẢN) ---

    // Hỗ trợ Activity để tích hợp Compose
    implementation("androidx.activity:activity-compose:1.8.2")

    // Cốt lõi của Compose UI
    implementation("androidx.compose.ui:ui:1.5.4")

    // !!! ĐÃ SỬA: Thêm phiên bản để giải quyết lỗi 'foundation'
    implementation("androidx.compose.foundation:foundation:1.5.4")

    // !!! ĐÃ SỬA: Thêm phiên bản để giải quyết lỗi 'material3'
    implementation("androidx.compose.material3:material3:1.1.2")

    // Các công cụ để xem trước (Preview) trong Android Studio
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
    // Các công cụ cho Live Preview và Preview
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.5.4")

    // --- Dependencies Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Thư viện testing cho Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
}