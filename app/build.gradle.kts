import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "es.fjruiz.magictimer"
    compileSdk = 36

    defaultConfig {
        applicationId = "es.fjruiz.magictimer"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["label"] = "MagicTimerApp"
    }

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = getLocalProperty("keyPassword")
            storeFile = file(getLocalProperty("storeFile"))
            storePassword = getLocalProperty("storePassword")
        }
    }

    buildTypes {

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            manifestPlaceholders["label"] = "MTA Debug"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.serialization.json)

    // DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // LifeCycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    // UI - Compose/Material
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.fonts)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons)

    // UI - Images
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Modules
    implementation(project(":components"))
    implementation(project(":data"))
    implementation(project(":domain"))

    // Common
    implementation(libs.fjruiz.common.kotlin)
    implementation(libs.fjruiz.common.compose)

    // Testing
    testImplementation(libs.junit)
    testImplementation(kotlin("test"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

fun Project.getLocalProperty(key: String, file: String = "local.properties"): String {
    val properties = Properties()
    val localProperties = File(file)
    return if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
        properties.getProperty(key)
    } else {
        System.getenv(key)
    }
}