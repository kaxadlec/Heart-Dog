import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
//    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
    kotlin("android")
    kotlin("plugin.serialization")
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.google.android.horologist.datalayer.sample"

        minSdk = 23
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        /* local 변수 */
        buildConfigField("String", "KAKAO_APP_KEY", gradleLocalProperties(rootDir, providers).getProperty("KAKAO_APP_KEY"))
        buildConfigField("String", "SUPABASE_URL", gradleLocalProperties(rootDir, providers).getProperty("SUPABASE_URL"))
        buildConfigField("String", "SUPABASE_ANON_KEY", gradleLocalProperties(rootDir, providers).getProperty("SUPABASE_ANON_KEY"))
        addManifestPlaceholders(
            mapOf(
                "KAKAO_API_KEY" to gradleLocalProperties(rootDir, providers).getProperty("KAKAO_APP_KEY"),
//                "KAKAO_REDIRECT_URL" to gradleLocalProperties(rootDir, providers).getProperty("KAKAO_REDIRECT_URL")
            )
        )

    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            manifestPlaceholders["schemeSuffix"] = "-debug"
        }

        release {
            manifestPlaceholders["schemeSuffix"] = ""

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion

        // Allow for widescale experimental APIs in Alpha libraries we build upon
        freeCompilerArgs = freeCompilerArgs +
                listOf(
                    "-opt-in=com.google.android.horologist.annotations.ExperimentalHorologistApi",
                )
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.google.android.horologist.datalayer.sample"
}

// 추가된 repositories 블록
repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
}

dependencies {

    val cameraxVersion = "1.1.0"

    implementation ("androidx.camera:camera-camera2:$cameraxVersion")
    implementation ("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation ("androidx.camera:camera-view:$cameraxVersion")

    api(projects.annotations)

    implementation(projects.datalayer.core)
    implementation(projects.datalayer.grpc)
    implementation(projects.datalayer.phone)
    implementation(projects.datalayer.phoneUi)
    implementation(projects.datalayer.sample.shared)

    implementation(libs.androidx.corektx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.toolingpreview)
    implementation(libs.compose.material3)
    implementation(libs.playservices.wearable)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.material.iconscore)
    implementation(libs.compose.material.iconsext)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.dagger.hiltandroid)
    implementation(libs.androidx.storage)
    ksp(libs.dagger.hiltandroidcompiler)
    implementation(libs.hilt.navigationcompose)

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    androidTestImplementation(libs.androidx.test.runner)

    implementation(libs.mpandroidchart)
    implementation(libs.accompanist.permissions)

    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.1"))
    implementation("io.github.jan-tennert.supabase:auth-kt")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:3.0.0")

    /* Google Play Location */
    implementation(libs.play.services.location)

    /* Permissions */
    implementation(libs.accompanist.permissions)

    /* Worker */
    implementation(libs.androidx.work.ktx)

    /* Login */
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.libraries.identity.googleid)
    implementation(libs.kakao.v2.user)
}
