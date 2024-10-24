/*
 * Copyright 2022 The Android Open Source Project
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
    id("com.gradle.develocity") version "3.18.1"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

rootProject.name = "hotdog-release"

include(":annotations")
include(":datalayer:core")
include(":datalayer:grpc")
include(":datalayer:watch")
include(":datalayer:phone")
include(":datalayer:phone-ui")
include(":datalayer:sample:phone")
include(":datalayer:sample:shared")
include(":datalayer:sample:wear")
include(":tiles")
include(":compose-tools")
include(":compose-layout")
include(":compose-material")
include(":composables")
include(":images:base")
include(":images:coil")

// https://docs.gradle.org/7.4/userguide/declaring_dependencies.html#sec:type-safe-project-accessors

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//val media3Checkout: String by settings
//
//if (media3Checkout.isNotBlank()) {
//    gradle.extra.set("androidxMediaModulePrefix", "media3-")
//    apply(from = file("$media3Checkout/core_settings.gradle"))
//}
include(":compose-material")
