plugins {
    id(Plugins.app)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kapt)
    id(Plugins.parcelize)
    id(Plugins.hilt)
    id(Plugins.safeArgs)
}

android {
    namespace = "com.mogo.moviescatalogue"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.mogo.moviescatalogue"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = Dependencies.testRunner
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    // module dependency
    implementation(project(Module.domain))
    implementation(project(Module.data))

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

    // Unit Testing
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutinesTest)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.archCoreTest)
    testImplementation(TestDependencies.mockitoInline)

}