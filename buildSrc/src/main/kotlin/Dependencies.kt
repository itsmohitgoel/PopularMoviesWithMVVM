object Versions {
    const val kotlin = "1.9.0"
    const val androidGradlePlugin = "8.2.0"
    const val safeVarargs = "2.5.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.2"
    const val androidXCore = "1.12.0"
    const val junit = "4.13.2"
    const val mockk = "1.11.0"
    const val coroutines = "1.6.4"
    const val mockito = "4.8.0"
    const val material = "1.10.0"
    const val lifecycle = "2.6.2"
    const val hilt = "2.48"
    const val hiltCompiler = "1.1.0"
    const val archCoreTest = "2.2.0"
    const val activityCompose = "1.8.2"
    const val compose = "1.6.0"
    const val composeNavigation = "2.7.6"
    const val hiltComposeNavigation = "1.1.0"
    const val coilCompose = "1.3.1"
}

object Plugins {
    const val app = "com.android.application"
    const val library = "com.android.library"
    const val jetbrainsKotlin = "org.jetbrains.kotlin.android"
    const val kapt = "kotlin-kapt"
    const val parcelize = "kotlin-parcelize"
    const val hilt = "com.google.dagger.hilt.android"
    const val safeArgs = "androidx.navigation.safeargs"
}

object Module {
    const val common = ":common"
    const val app = ":app"
    const val data = ":data"
    const val domain = ":domain"
}

object ProjectConfig {
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
}

object Dependencies {
    //android
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val viewModelLifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"

    //compose
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val hiltComposeNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"

    //hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"

    //network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}