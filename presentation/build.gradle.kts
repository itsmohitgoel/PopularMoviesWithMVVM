plugins {
    id(Plugins.library)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kapt)
    id(Plugins.parcelize)
    id(Plugins.hilt)
}

android {
    namespace = "com.mogo.presentation"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        testInstrumentationRunner = Dependencies.testRunner
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    // module dependency
    implementation(project(Module.domain))

    //android
    implementation(Dependencies.material)
    implementation(Dependencies.androidXCore)
    implementation(Dependencies.viewModelLifecycle)
    //compose
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.hiltComposeNavigation)
    implementation(Dependencies.coilCompose)

    //Dagger - Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.androidXCore)

    testImplementation(TestDependencies.coroutinesTest)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.turbineTest)
}