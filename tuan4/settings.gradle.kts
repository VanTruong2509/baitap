pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    // ⚙️ Thêm plugin Compose Compiler cho Kotlin 2.0+
    plugins {
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" // hoặc version Kotlin bạn đang dùng
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "tuan4"
include(":app")
