plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.moviedbopenplay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moviedbopenplay"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation(libs.androidx.hilt.navigation.compose)


    //okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)



//    implementation(files("libs/gson-2.2.4.jar"))

    //lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)


    // ... other dependencies

    implementation(libs.material3)
    implementation(libs.kotlinx.serialization.json)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //MoshiConverter
    implementation(libs.moshi.kotlin)
    // ... other dependencies

    //coil
    implementation(libs.coil.compose)

    //palette API
    implementation(libs.androidx.palette.ktx)


    //bottomnav
    implementation(libs.androidx.material)

    //datastore
    implementation(libs.androidx.datastore.preferences)
    implementation(kotlin("script-runtime"))

    //ROOM
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor( "androidx.room:room-compiler:2.6.1")

    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
    kapt("androidx.room:room-compiler:2.6.1")
    implementation( "androidx.room:room-ktx:2.6.1")
}
kapt{
    correctErrorTypes = true

}




