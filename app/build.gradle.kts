plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.wastebin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wastebin"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}
val versionRetrofit = "2.9.0"
dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
//    implementation ("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("com.github.hannesa2:paho.mqtt.android:3.3.5")
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")

    implementation ("com.squareup.retrofit2:retrofit:$versionRetrofit")
    implementation ("com.squareup.retrofit2:converter-gson:$versionRetrofit")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
//    implementation ("com.github.AAChartModel:AAChartCore-Kotlin:-SNAPSHOT")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}