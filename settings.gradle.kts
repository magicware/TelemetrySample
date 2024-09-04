rootProject.name = "TelemetrySample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://androidx.dev/kmp/builds/12227334/artifacts/snapshots/repository")
        maven("https://androidx.dev/snapshots/builds/12227334/artifacts/repository")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://mymavenrepo.com/repo/q4vqkfpPHT4X1N7Qxui5/")
        maven("https://androidx.dev/kmp/builds/12227334/artifacts/snapshots/repository")
        maven("https://androidx.dev/snapshots/builds/12227334/artifacts/repository")
    }
}

include(":composeApp")