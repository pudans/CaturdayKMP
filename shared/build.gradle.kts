import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.compose")
//    kotlin("plugin.serialization")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
       browser()
    }
    
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
//            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//            implementation(compose.components.resources)

            api("moe.tlaster:precompose:1.6.0-beta02")
//            api("moe.tlaster:precompose-molecule:1.6.0-beta02") // For Molecule intergration
            api("moe.tlaster:precompose-viewmodel:1.6.0-beta02") // For ViewModel intergration
            api("moe.tlaster:precompose-koin:1.6.0-beta02") // For Koin intergration

            implementation(libs.kotlinx.serialization.json)
//            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)

            // Dependency Injection
            implementation(libs.koin.core)
            implementation("io.insert-koin:koin-compose:3.6.0-wasm-alpha2")
//            implementation("io.insert-koin:koin-ktor:3.6.0-wasm-alpha2")
//            implementation("io.insert-koin:koin-core:3.6.0-wasm-alpha2")
//            implementation("io.insert-koin:koin-core:3.6.0-wasm-alpha2")
//            implementation("io.insert-koin:koin-core:3.6.0-wasm-alpha2")

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
