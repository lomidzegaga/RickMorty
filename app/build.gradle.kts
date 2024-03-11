plugins {
    alias(libs.plugins.android.aplication)
    alias(libs.plugins.kotlin)

    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.rickmorty"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rickmorty"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    libs.apply {
        implementation(platform(compose.bom))
        implementation(material3)

        // Retrofit
        implementation(retrofit)
        implementation(okhttp.logging)

        // Hilt
        implementation(hilt)
        ksp(hilt.anotation)

        // Gson
        implementation(gson)

        // Glide
        implementation(glide)

        androidx.apply {
            implementation(core)
            implementation(lifecycle)
            implementation(activity.compose)
        }
    }
}