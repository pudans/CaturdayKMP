import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//       browser()
//    }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.animation)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            api(libs.precompose)
            api(libs.precompose.viewmodel) // For ViewModel intergration
            api(libs.precompose.koin) // For Koin intergration

            implementation(libs.kotlinx.serialization.json)
//            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)

            // Dependency Injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }
    }
}

android {
    namespace = "app.caturday.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
