plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.bewell"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bewell"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("E:/Projects2025/BeWell/app/bewell.jks") // Path to your keystore file
            storePassword = "bewell123"
            keyAlias = "bewell123"
            keyPassword = "bewell123"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)


    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime.livedata)

    //Permission
    implementation(libs.accompanist.permissions)

    //Koin
    implementation(libs.koin.androidx.compose)

    //Coil
    implementation(libs.coil.compose)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    //KSP
    ksp(libs.androidx.room.compiler)

    //splash screen library
    implementation(libs.androidx.core.splashscreen)

    // ✅ ARCore
    implementation(libs.core)

    // ✅ SceneView (AR + 3D Model Support)
    implementation(libs.arsceneview)

}