

plugins {
    id(Plugins.library)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kapt)
    id(Plugins.parcelize)
    id(Plugins.hilt)
}

android {
    namespace = "com.mogo.data"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
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
    implementation(project(Module.domain))

    //Dagger - Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConvertor)
    implementation (Dependencies.okHttp)
    implementation (Dependencies.loggingInterceptor)

    implementation(Dependencies.androidXCore)

    testImplementation(TestDependencies.coroutinesTest)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.junit)
}