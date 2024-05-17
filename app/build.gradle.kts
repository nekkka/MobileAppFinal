plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.finalprojectstocks"
    compileSdk = 34

    defaultConfig {
        buildConfigField(
            "String",
            "API_KEY",
            "\"${project.findProperty("API_KEY")}\""
        )
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true

    }

    defaultConfig {
        applicationId = "com.example.finalprojectstocks"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //hilt
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)


    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //coroutines
    implementation(libs.coroutines)

    //navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    //coil
    implementation(libs.coil)

    //DateTime
    implementation(libs.kotlinx.datetime)

    //Charts
    implementation(libs.charts)

    //Picker
    implementation(libs.number.picker)
}


kapt {
    correctErrorTypes = true
}