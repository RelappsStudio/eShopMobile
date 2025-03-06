plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.relapps.eshopmobile"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.relapps.eshopmobile"
        minSdk = 24
        targetSdk = 35
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.material3)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-compiler:2.55")
    //AI GENERATED DEPS ABOVE
//    implementation(project(":domain"))
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation("androidx.compose.material3:material3:1.4.0-alpha07")
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.livedata)
    implementation (libs.logging.interceptor)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.runtime.livedata)
//
//    // Core Hilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//
//    // ViewModel dependencies
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    kapt(libs.androidx.lifecycle.compiler)
//    implementation( libs.androidx.hilt.lifecycle.viewmodel)
//    implementation (libs.androidx.hilt.work )
//    kapt(libs.hilt.compiler)
//    kapt(libs.hilt.android.compiler)
//    implementation(libs.hilt.android)
//    implementation (libs.androidx.navigation.compose)
//    implementation(libs.androidx.room.runtime)
//    kapt(libs.androidx.room.compiler) // For Java
////    ksp("androidx.room:room-compiler:$roomVersion") // For Kotlin
//    implementation(libs.androidx.room.ktx)
//    kapt(libs.kotlinx.metadata.jvm)

//    // Hilt (or Dagger)
//    val hiltVersion = "2.50" // Or latest stable version
//    implementation("com.google.dagger:hilt-android:$hiltVersion")
//    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

}

kapt {
    correctErrorTypes = true
}