plugins {
    id(Plugins.library)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kapt)
    id(Plugins.parcelize)
    id(Plugins.hilt)
}

android {
    namespace = "com.mogo.domain"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

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
}

dependencies {
    //Dagger - Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.androidXCore)

    testImplementation(TestDependencies.coroutinesTest)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockk)
}